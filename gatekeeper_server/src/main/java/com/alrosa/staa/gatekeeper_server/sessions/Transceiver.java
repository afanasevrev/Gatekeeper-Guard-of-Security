package com.alrosa.staa.gatekeeper_server.sessions;

import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

/**
 * Класс для отправки сообщений клиенту
 */
public class Transceiver {
    public void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
            String text = "Первый в космосе";
            channel.basicPublish("", Variables.QUEUE_NAME, null, text.getBytes(StandardCharsets.UTF_8));
        }
    }
}
