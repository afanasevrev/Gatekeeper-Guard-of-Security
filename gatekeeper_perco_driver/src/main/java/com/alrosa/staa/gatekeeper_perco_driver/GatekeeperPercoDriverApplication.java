package com.alrosa.staa.gatekeeper_perco_driver;

import com.alrosa.staa.gatekeeper_perco_driver.service.MyWebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatekeeperPercoDriverApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatekeeperPercoDriverApplication.class, args);
		MyWebSocketClient myWebSocketClient = new MyWebSocketClient();
		myWebSocketClient.connect();
	}
}
