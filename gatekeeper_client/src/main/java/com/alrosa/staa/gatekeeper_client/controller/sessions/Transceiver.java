package com.alrosa.staa.gatekeeper_client.controller.sessions;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Класс для отправки сообщений к серверу посредством RabbitMQ
 */
public class Transceiver {
    public void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
            Main main = new Main("Главный");
            Gson gson = new Gson();
            String text = gson.toJson(main);
            channel.basicPublish("", Variables.QUEUE_NAME, null, text.getBytes(StandardCharsets.UTF_8));
            System.out.println(text);
        }
    }
}
