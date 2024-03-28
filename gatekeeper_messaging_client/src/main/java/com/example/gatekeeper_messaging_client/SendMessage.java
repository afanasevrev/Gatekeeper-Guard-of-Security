package com.example.gatekeeper_messaging_client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {

    @Autowired
    RabbitTemplate template;

    public String send(String text) {
        String response = (String) template.convertSendAndReceive(text);
        return response;
    }
}
