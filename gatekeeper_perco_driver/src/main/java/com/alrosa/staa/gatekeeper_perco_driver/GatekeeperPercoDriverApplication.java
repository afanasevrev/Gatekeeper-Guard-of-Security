package com.alrosa.staa.gatekeeper_perco_driver;

import com.alrosa.staa.gatekeeper_perco_driver.repository.Storage;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class
GatekeeperPercoDriverApplication {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(GatekeeperPercoDriverApplication.class, args);
		Storage.storageCards.add("382499");
	}
}
