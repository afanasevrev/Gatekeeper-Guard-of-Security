package com.alrosa.staa.gatekeeper_server.messaging;

import com.alrosa.staa.gatekeeper_server.db.General;
import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.google.gson.Gson;
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

    Gson gson = new Gson();

    @Autowired
    private AmqpTemplate template;
    /**
     * Листенер получает от клиента объекты,
     * которые необходимо добавить в БД,
     * потом вернуть обратно объект клиенту,
     * чтобы тот у себя добавил в дереве
     */
    @RabbitListener(queues = Variables.QUEUE_NAME)
    private void Queue(String message) {
        logger.info(message);
        General general = gson.fromJson(message, General.class);
        logger.info(general.getDirection());
        //template.convertAndSend(Variables.QUEUE_NAME_1, "Otvet poluchen");
    }
}
