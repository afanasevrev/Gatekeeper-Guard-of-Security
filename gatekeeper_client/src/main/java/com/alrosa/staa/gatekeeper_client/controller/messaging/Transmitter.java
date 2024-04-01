package com.alrosa.staa.gatekeeper_client.controller.messaging;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Класс отвечает за отправку сообщений на сервер
 * Реализуем его по паттерну Singleton
 */
public class Transmitter {
    private static Transmitter INSTANCE;
    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private Transmitter(){}
    public synchronized static Transmitter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Transmitter();
        }
        return INSTANCE;
    }
    /**
     * Метод для отправки сообщения серверу
     * @param text
     * @throws IOException
     * @throws TimeoutException
     */
    public synchronized void sendMessage(String text) throws IOException, TimeoutException {
        connectionFactory.setHost(Variables.properties.getProperty("server_ip"));
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //channel.queueDeclare(Variables.QUEUE_NAME, true, false, false, null);
        channel.basicPublish("", Variables.QUEUE_NAME, null, text.getBytes(StandardCharsets.UTF_8));
    }
}
