package com.alrosa.staa.gatekeeper_server.sessions;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    //Настраиваем соединение с сервером RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory());
        return amqpAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setReplyTimeout(60 * 1000);
        return rabbitTemplate;
    }

    //Объявляем очередь с именем "Gatekeeper"
    @Bean
    public Queue MyQueue() {
        return new Queue("Gatekeeper");
    }
}
