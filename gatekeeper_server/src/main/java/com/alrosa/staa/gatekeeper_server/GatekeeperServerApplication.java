package com.alrosa.staa.gatekeeper_server;

import com.alrosa.staa.gatekeeper_server.session.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatekeeperServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GatekeeperServerApplication.class, args);

		Receiver receiver = new Receiver();
		receiver.start();
	}
}
