package com.alrosa.staa.gatekeeper_server.messaging;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @Autowired
    private AmqpTemplate template;

    @RabbitListener(queues = "Gatekeeper")
    private void Queue(String message) {
        logger.info(message);
    }
}
