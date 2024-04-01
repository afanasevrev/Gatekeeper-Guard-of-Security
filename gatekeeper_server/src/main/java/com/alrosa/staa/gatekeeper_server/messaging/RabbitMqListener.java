package com.alrosa.staa.gatekeeper_server.messaging;

import com.alrosa.staa.gatekeeper_server.variables.Variables;
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

    @RabbitListener(queues = Variables.QUEUE_NAME)
    private void Queue(String message) {
        logger.info(message);
        template.convertAndSend(Variables.QUEUE_NAME_1, "Otvet poluchen");
    }
}
