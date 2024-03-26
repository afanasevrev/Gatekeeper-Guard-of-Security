package com.example.gatekeeper_messaging;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    //Настраиваем соединение с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //Объявляем очередь с именем "Gatekeeper"
    @Bean
    public Queue MyQueue() {
        return new Queue("Gatekeeper");
    }

    //Объявляем контейнер, который будет содержать листенер для сообщений
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("Gatekeeper");
        container.setMessageListener(new MessageListener() {
            //Тут ловим сообщения из Gatekeeper
            public void onMessage(Message message) {
                logger.info("received from Gatekeeper : " + new String(message.getBody()));
            }
        });
        return container;
    }
}
