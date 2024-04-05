package com.alrosa.staa.gatekeeper_server;

import com.alrosa.staa.gatekeeper_server.messaging.RabbitConfiguration;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Точка запуска сервера
 */
@SpringBootApplication
@Import(RabbitConfiguration.class)
@EnableAutoConfiguration
@ComponentScan
public class GatekeeperServerApplication {

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		//Запускаем сервер Spring
		SpringApplication.run(GatekeeperServerApplication.class, args);
	}
}
