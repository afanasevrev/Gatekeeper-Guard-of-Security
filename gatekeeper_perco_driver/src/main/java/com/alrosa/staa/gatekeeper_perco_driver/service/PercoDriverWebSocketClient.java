package com.alrosa.staa.gatekeeper_perco_driver.service;

import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.ControlData;
import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.Exdev;
import com.alrosa.staa.gatekeeper_perco_driver.messages.EventCard;
import com.alrosa.staa.gatekeeper_perco_driver.repository.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class PercoDriverWebSocketClient extends TextWebSocketHandler {
    private Logger logger = Logger.getLogger(PercoDriverWebSocketClient.class);
    private Gson gson = new Gson();
    private WebSocketSession session;
    /**
     * Создаем экземпляр класса ControlData для отправки команды на контроллер
     */
    private ControlData controlData = new ControlData();
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 0
     * direction - направление, здесь - 0
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле - 3 секунды
     */
    private Exdev exdev0 = new Exdev(0, 0, "open", "open once", 3000);
    /**
     * Создаем экземпляр класса Exdev
     * number - номер исполняемого устройства, здесь - 0
     * direction - направление, здесь - 1
     * open_type - open once что означает, открыть для однократного прохода
     * open_time - время удержания реле - 3 секунды
     */
    private Exdev exdev1 = new Exdev(0, 1, "open", "open once", 3000);

    public void connect(String ip_address) {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        try {
            this.session = client.execute(this, headers, new URI("ws://" + ip_address + "/tcp")).get();
            logger.info("Подключение установлено к контроллеру: " + ip_address + " " + this.session.getId());
        } catch (ExecutionException e) {

        } catch (URISyntaxException e) {
            logger.error(e);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
    public void sendMessage(String message) {
        try {
            this.session.sendMessage(new TextMessage(message));
            logger.info("Отправлена команда: " + message);
        } catch (Exception e) {
            logger.error("Возникла ошибка при отправке сообщения");
        }
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws NullPointerException {
        String jsonString = message.getPayload();
        logger.info("Получено сообщение: " + jsonString);
        try {
            EventCard eventCard = gson.fromJson(jsonString, EventCard.class);
            if (Storage.storageCards.contains(eventCard.getCard().getId())) {
                controlData.setControl("exdev");
                controlData.setExdev(exdev0);
                String text = gson.toJson(controlData);
                sendMessage(text);
            }
            logger.info(eventCard.getCard().getId());
        } catch (NullPointerException | JsonSyntaxException e) {
            logger.error("Ошибка синтаксиса JSON");
        }
    }
}
