package com.alrosa.staa.gatekeeper_client.controller.sessions;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Receiver {

    public void start() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Variables.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Server RabbitMQ is started");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume(Variables.QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
