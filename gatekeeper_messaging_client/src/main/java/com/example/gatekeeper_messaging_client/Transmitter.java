package com.example.gatekeeper_messaging_client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * Класс для отправки сообщений на сервер посредством RabbitMQ
 */
public class Transmitter {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public void sendMessage(String text) throws Exception {
        connectionFactory.setHost("127.0.0.1");
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel())
        {
            channel.queueDeclare("Gatekeeper", true, false, false, null);
            channel.basicPublish("", "Gatekeeper", null, text.getBytes(StandardCharsets.UTF_8));
        }
    }
}
