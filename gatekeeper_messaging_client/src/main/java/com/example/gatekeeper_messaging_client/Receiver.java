package com.example.gatekeeper_messaging_client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Класс для приёма сообщений от сервера
 */
public class Receiver {
    ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * Метод запускает приёмник сообщений
     */
    public void start() throws IOException, TimeoutException {
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("Gatekeeper1", true, false, false, null);

        System.out.println("[*] Receiver RabbitMQ is started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume("Gatekeeper1", true, deliverCallback, consumerTag -> { });
    }
}
