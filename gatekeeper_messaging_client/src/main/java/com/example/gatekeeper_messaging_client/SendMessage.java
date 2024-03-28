package com.example.gatekeeper_messaging_client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
@AutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class SendMessage {
    @Autowired
    AmqpTemplate template;

    public String send(String text) {
        String response = (String) template.convertSendAndReceive(text);
        return response;
    }
}
