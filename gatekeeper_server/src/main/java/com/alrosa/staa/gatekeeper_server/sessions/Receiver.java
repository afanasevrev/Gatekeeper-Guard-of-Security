package com.alrosa.staa.gatekeeper_server.sessions;

import com.alrosa.staa.gatekeeper_server.db.Main;
import com.alrosa.staa.gatekeeper_server.util.HibernateUtil;
import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс - приёмник сообщений от сервера
 */
public class Receiver {
    //Создаем экземпляр класса Transceiver
    Transceiver transceiver = new Transceiver();
    ConnectionFactory factory = new ConnectionFactory();

    Gson gson = new Gson();

    String text = new String();

    /**
     * Метод запускает приёмник сообщений
     */
    public void start() throws Exception {
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
        System.out.println(Variables.date + " Server RabbitMQ is started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(message);
            List<Main> mainObjects = getMainObjects();
            text = gson.toJson(mainObjects);
            try {
                transceiver.send(text);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        channel.basicConsume(Variables.QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    /**
     * Метод возвращает список главных объектов из БД
     * @return
     */
    private List<Main> getMainObjects() {
        Transaction transaction = null;
        List<Main> mainObjects = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            mainObjects = session.createQuery("from Main", Main.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return mainObjects;
    }
}
