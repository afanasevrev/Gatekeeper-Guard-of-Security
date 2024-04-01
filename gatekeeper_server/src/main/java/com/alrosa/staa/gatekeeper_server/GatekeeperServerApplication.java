package com.alrosa.staa.gatekeeper_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка запуска сервера
 */
@SpringBootApplication
public class GatekeeperServerApplication {
	public static void main(String[] args) throws Exception {
		//Запускаем сервер Spring
		SpringApplication.run(GatekeeperServerApplication.class, args);
	}
}
