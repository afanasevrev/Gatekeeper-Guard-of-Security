package com.alrosa.staa.gatekeeper_perco_driver.service;

import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.ControlData;
import com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands.Exdev;
import com.alrosa.staa.gatekeeper_perco_driver.messages.EventCard;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.net.URI;
public class PercoDriverWebSocketClient extends TextWebSocketHandler {
    private Gson gson = new Gson();
    private WebSocketSession session;
    public void connect() {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        try {
            this.session = client.doHandshake(this, headers, new URI("ws://10.2.221.26/tcp")).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to WebSocket server", e);
        }
    }
    public void sendMessage(String message) {
        try {
            this.session.sendMessage(new TextMessage(message));
            System.out.println("Sent message: " + message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws NullPointerException {
        String jsonString = message.getPayload();
        System.out.println("Received message: " + jsonString);
        try {
            EventCard eventCard = gson.fromJson(jsonString, EventCard.class);
            if(eventCard.getCard().getId().equals("3867329")) {
                ControlData controlData = new ControlData();
                controlData.setControl("exdev");
                controlData.setExdev(new Exdev(0, 0, "open", "open once", 5000));
                String text = gson.toJson(controlData);
                sendMessage(text);
            }
            System.out.println(eventCard.getCard().getId());
        } catch (NullPointerException | JsonSyntaxException e) {
            System.out.println("Ошибка JSON");
        }
    }
}
