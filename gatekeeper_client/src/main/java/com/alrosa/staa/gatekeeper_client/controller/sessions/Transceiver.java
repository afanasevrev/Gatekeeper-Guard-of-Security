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
    private static Transceiver INSTANCE;
    private Transceiver() {}
    public static Transceiver getTransceiver () {
        if (INSTANCE == null) {
            return new Transceiver();
        } else {
            return INSTANCE;
        }
    }
    ConnectionFactory factory = new ConnectionFactory();

    //Вытягиваем настройки подключения из конфигурационного файла
    String server_ip = Variables.properties.getProperty("server_ip");

    /**
     * Отправляем сообщение на сервер
     * @throws Exception
     */
    public synchronized void send(String text) throws Exception {
        factory.setHost(server_ip);
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", Variables.QUEUE_NAME, null, text.getBytes(StandardCharsets.UTF_8));
            System.out.println(text);
        }
    }
}
