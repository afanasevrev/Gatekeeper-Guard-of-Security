package com.example.gatekeeper_messaging;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    @RabbitListener(queues = "Gatekeeper")
    public void processQueue(String message) {
        logger.info("Received from Gatekeeper: " + message);
    }
}