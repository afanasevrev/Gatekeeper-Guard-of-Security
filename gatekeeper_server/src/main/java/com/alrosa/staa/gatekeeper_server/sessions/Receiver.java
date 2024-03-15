package com.alrosa.staa.gatekeeper_server.sessions;

import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * Класс - приёмник сообщений от сервера
 */
public class Receiver {
    //Создаем экземпляр класса Transceiver
    Transceiver transceiver = new Transceiver();

    ConnectionFactory factory = new ConnectionFactory();
    /**
     * Метод запускает приёмник сообщений
     */
    public void start() throws Exception {
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Server RabbitMQ is started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            try {
                transceiver.send();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        channel.basicConsume(Variables.QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
