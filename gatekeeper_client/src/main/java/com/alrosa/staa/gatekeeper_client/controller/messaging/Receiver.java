package com.alrosa.staa.gatekeeper_client.controller.messaging;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Класс Приёмник сообщений от сервера
 * Класс реализуем по паттерну Singleton
 */
public class Receiver {

    Logger logger = Logger.getLogger(Receiver.class);

    private static Receiver INSTANCE;

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    private Receiver(){}
    public synchronized static Receiver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Receiver();
        }
        return INSTANCE;
    }

    /**
     * Метод запускает приёмник сообщений
     */
    public void start() throws IOException, TimeoutException {
        connectionFactory.setHost(Variables.properties.getProperty("server_ip"));
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Variables.QUEUE_NAME_1, true, false, false, null);
        logger.info("Receiver is started");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.info("Received from the server: " + message);
        };
        channel.basicConsume(Variables.QUEUE_NAME_1, true, deliverCallback, consumerTag -> {});
    }
}
