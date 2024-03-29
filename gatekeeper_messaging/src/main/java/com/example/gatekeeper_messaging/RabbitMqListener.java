package com.example.gatekeeper_messaging;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    /**
     * Слушатель сообщений от клиентов
     * @param message
     * @return
     * @throws InterruptedException
     */
    //@RabbitListener(queues = "Gatekeeper")
   // public String processQueue(String message) throws InterruptedException {
   //     logger.info("Received from Gatekeeper: " + message);
    //    Thread.sleep(2000);
    //    return "Received on worker: " + message;
    //}

    @RabbitListener(queues = "Gatekeeper")
    public void processQueue1(String message) throws InterruptedException {
        logger.info("Received from Gatekeeper: " + message);
        Thread.sleep(2000);
    }
}
