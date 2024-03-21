package com.alrosa.staa.gatekeeper_client.controller.sessions;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * Класс для приёма сообщений от сервера
 */
public class Receiver {
    private String client_ip = Variables.properties.getProperty("client_ip");
    private ConnectionFactory factory = new ConnectionFactory();
    /**
     * Метод запускает приёмник сообщений от сервера
     * @throws Exception
     */
    public void start() throws Exception {
        factory.setHost(client_ip);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Variables.QUEUE_NAME_1, false, false, false, null);
        System.out.println(Variables.date + " [*] Receiver RabbitMQ is started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume(Variables.QUEUE_NAME_1, true, deliverCallback, consumerTag -> { });
    }
}
