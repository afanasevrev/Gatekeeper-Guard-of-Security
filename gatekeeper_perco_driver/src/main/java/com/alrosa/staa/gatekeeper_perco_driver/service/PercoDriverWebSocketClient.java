package com.alrosa.staa.gatekeeper_perco_driver.service;

import com.alrosa.staa.gatekeeper_perco_driver.messages.EventCard;
import com.google.gson.Gson;
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
            System.out.println(eventCard.getCard().getId());
        } catch (NullPointerException e) {
            System.out.println("Ошибка JSON");
        }
    }
}
