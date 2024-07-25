package com.alrosa.staa.gatekeeper_perco_driver.service;

import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.ControlData;
import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.Exdev;
import com.alrosa.staa.gatekeeper_perco_driver.general.General;
import com.alrosa.staa.gatekeeper_perco_driver.general.MessageType;
import com.alrosa.staa.gatekeeper_perco_driver.messages.*;
import com.alrosa.staa.gatekeeper_perco_driver.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
/**
 * Класс устанавливает соединение с контроллером
 */
public class PercoDriverWebSocketClient extends TextWebSocketHandler {
    private Logger logger = Logger.getLogger(PercoDriverWebSocketClient.class);
    private Gson gson = new Gson();
    private WebSocketSession session;
    private RestTemplate restTemplate = new RestTemplate();
    //IP - адрес контроллера сохраним здесь
    private String ipAddress;
    //URL адрес куда будет драйвер высылать сообщения от контроллера
    private String url_server = "http://localhost:8080/fromController";
    //Текущая дата
    private Date currentDate;
    //Форматируем дату на свое усмотрнеие
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    //Отформатированная дата. Нужна для отправки на сервер
    private String formattedDate;
    /**
     * Создаем экземпляр класса ControlData для отправки команды на контроллер
     */
    private ControlData controlData = new ControlData();
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 0
     * direction - направление, здесь - 0
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле открытой - 3 секунды
     */
    private Exdev exdev00 = new Exdev(0, 0, "open", "open once", 3000);
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 0
     * direction - направление, здесь - 1
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле открытой - 3 секунды
     */
    private Exdev exdev01 = new Exdev(0, 1, "open", "open once", 3000);
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 1
     * direction - направление, здесь - 0
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле открытой - 3 секунды
     */
    private Exdev exdev10 = new Exdev(1, 0, "open", "open once", 3000);
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 1
     * direction - направление, здесь - 1
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле открытой - 3 секунды
     */
    private Exdev exdev11 = new Exdev(1, 1, "open", "open once", 3000);
    /**
     * Метод устанавливает соединение с контроллером
     * @param ip_address
     */
    public void connect(String ip_address) {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        try {
            this.session = client.execute(this, headers, new URI("ws://" + ip_address + "/tcp")).get();
            this.ipAddress = ip_address;
            logger.info("Подключение установлено c контроллером: " + ip_address + " " + this.session.getId());
        } catch (ExecutionException e) {}
          catch (URISyntaxException e) {
            logger.error(e);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
    /**
     * Метод отправляет сообщение на контроллер
     * @param message
     */
    public void sendMessage(String message) {
        try {
            this.session.sendMessage(new TextMessage(message));
            logger.info("Отправлена команда: " + message);
        } catch (Exception e) {
            logger.error("Возникла ошибка при отправке сообщения");
        }
    }
    /**
     * Метод ловит сообщения от контроллера
     * @param session сессия
     * @param message сообщение
     * @throws NullPointerException
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws NullPointerException {
        String jsonString = message.getPayload();
        logger.info("Получено сообщение: " + jsonString);
        try {
            EventCard eventCard = gson.fromJson(jsonString, EventCard.class);
            if (Storage.storageCards.contains(eventCard.getCard().getId())) {
                controlData.setControl("exdev");
                if (eventCard.getCard().getNumber() == 0 && eventCard.getCard().getDirection() == 0) {
                    controlData.setExdev(exdev00);
                } else if (eventCard.getCard().getNumber() == 0 && eventCard.getCard().getDirection() == 1) {
                    controlData.setExdev(exdev01);
                } else if (eventCard.getCard().getNumber() == 1 && eventCard.getCard().getDirection() == 0) {
                    controlData.setExdev(exdev10);
                } else if (eventCard.getCard().getNumber() == 1 && eventCard.getCard().getDirection() == 1) {
                    controlData.setExdev(exdev11);
                }
                String text = gson.toJson(controlData);
                sendMessage(text);
            }
        } catch (NullPointerException | JsonSyntaxException e) {}
        try {
            EventPassBanPersonal eventPassBanPersonal = gson.fromJson(jsonString, EventPassBanPersonal.class);
            logger.info("Неизвестная карта: " + eventPassBanPersonal.getPass_ban_personal().getId());
        } catch (NullPointerException | JsonSyntaxException e) {}
        try {
            if (jsonString.contains("exdev_unlock") && jsonString.contains("pass_personal")) {
                String regex = "\\s";
                String[] jsonString1 = jsonString.split(regex);
                EventExdevUnlock eventExdevUnlock = gson.fromJson(jsonString1[2], EventExdevUnlock.class);
                EventPassPersonal eventPassPersonal = gson.fromJson(jsonString1[4], EventPassPersonal.class);
                currentDate = new Date();
                formattedDate = simpleDateFormat.format(currentDate);
                General general = new General(MessageType.OPERATOR, formattedDate, eventPassPersonal.getPass_personal().getId(), ipAddress, eventExdevUnlock.getExdev_unlock().getNumber(), true);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<General> entity = new HttpEntity<General>(general, headers);
                restTemplate.exchange(url_server, HttpMethod.POST, entity, String.class);
                logger.info("Разрешен доступ: " + eventPassPersonal.getPass_personal().getId() + " Разблокирован ИУ: " + eventExdevUnlock.getExdev_unlock().getNumber());
            }
        } catch (NullPointerException | JsonSyntaxException e) {}
        try {
            EventExdevUnlock eventExdevUnlock = gson.fromJson(jsonString, EventExdevUnlock.class);
            if (!eventExdevUnlock.getExdev_unlock().isUnlock()) {
                logger.info("Заблокирован ИУ: " + eventExdevUnlock.getExdev_unlock().getNumber());
            }
        } catch(NullPointerException | JsonSyntaxException e) {}
    }
}
