package com.alrosa.staa.gatekeeper_server.messaging;

import com.alrosa.staa.gatekeeper_server.db.Direction;
import com.alrosa.staa.gatekeeper_server.db.General;
import com.alrosa.staa.gatekeeper_server.db.Main;
import com.alrosa.staa.gatekeeper_server.db.Server;
import com.alrosa.staa.gatekeeper_server.util.HibernateUtil;
import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    Gson gson = new Gson();

    String text;

    @Autowired
    private AmqpTemplate template;

    /**
     * Листенер получает от клиента объекты,
     * которые необходимо добавить в БД,
     * потом вернуть обратно с данными объект клиенту,
     * чтобы тот у себя добавил в дереве
     */
    @RabbitListener(queues = Variables.QUEUE_NAME)
    private void Queue(String message) {
        logger.info(message);
        General general = gson.fromJson(message, General.class);
        switch (general.getDirection()) {
            case MAIN:
                Main main = getMain(general.getId());
                general.setId(main.getId());
                general.setDirection(Direction.MAIN);
                general.setParentId(0);
                general.setComplete_name(main.getName());
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case SERVER:
                Server server = new Server("Сервер", "0.0.0.0", general.getParentId());
                try {
                    writeServer(server);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(server.getId());
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            default:
                template.convertAndSend(Variables.QUEUE_NAME_1, "Этот вопрос ещё не проработан");
                break;
        }
    }
    /**
     * Метод вытягивает из БД главный класс по указанному ID
     */
    private synchronized Main getMain(int id) {
        Main main = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            main = session.get(Main.class, id);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return main;
    }
    /**
     * Метод записывает в БД объект Сервер
     * @param server
     */
    private synchronized void writeServer(Server server) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(server);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
