package com.example.gatekeeper_messaging_client;

import org.apache.log4j.BasicConfigurator;

public class GatekeeperMessagingClientApplication {

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Transmitter transmitter = new Transmitter();
		transmitter.sendMessage("Hello Moscow!");
		Receiver receiver = new Receiver();
		receiver.start();
	}
}
