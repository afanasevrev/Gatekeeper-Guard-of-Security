package com.example.gatekeeper_messaging_client;

import org.apache.log4j.BasicConfigurator;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

public class GatekeeperMessagingClientApplication {

	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();

		SendMessage sendMessage = new SendMessage();

		System.out.println(sendMessage.send("Hello Revoly"));
	}
}
