package com.example.gatekeeper_messaging;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    @Autowired
    AmqpTemplate template;

    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    /**
     * Слушатель сообщений от клиентов
     * @param message
     * @return
     * @throws InterruptedException
     */

    /**
    @RabbitListener(queues = "Gatekeeper")
    public String processQueue(String message) throws InterruptedException {
        logger.info("Received from Gatekeeper: " + message);
        Thread.sleep(2000);
        return "Received on worker: " + message;
    }
    **/

    @RabbitListener(queues = "Gatekeeper")
    public void processQueue1(String message) throws InterruptedException {
        logger.info("Received from Gatekeeper: " + message);
        template.convertAndSend("Gatekeeper1", "Poluchil vashe soobshenie");
    }
}
