package com.example.gatekeeper_messaging_client;

import org.apache.log4j.BasicConfigurator;

public class GatekeeperMessagingClientApplication {

	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();

		SendMessage sendMessage = new SendMessage();

		System.out.println(sendMessage.send("Hello Revoly"));
	}
}
