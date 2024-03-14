package com.alrosa.staa.gatekeeper_client.controller.sessions;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

/**
 * Класс для отправки сообщений на сервер посредством RabbitMQ
 */
public class Transceiver {
    ConnectionFactory factory = new ConnectionFactory();

    String server_ip = Variables.properties.getProperty("server_ip");

    public void send() throws Exception {
        factory.setHost(server_ip);
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
