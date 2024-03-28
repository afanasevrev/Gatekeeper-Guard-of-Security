package com.example.gatekeeper_messaging_client;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;


public class SendMessage {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

   // @Autowired
   // AmqpTemplate template;

    public String send(String text) {
        String response = (String) rabbitTemplate.convertSendAndReceive(text);
        return response;
    }
}
