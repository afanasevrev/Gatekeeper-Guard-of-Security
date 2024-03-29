package com.example.gatekeeper_messaging;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Проект для обмена сообщениями посредством RabbitMQ
 */
@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class GatekeeperMessagingApplication {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(GatekeeperMessagingApplication.class, args);
	}
}
