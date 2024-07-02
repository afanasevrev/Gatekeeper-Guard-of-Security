package com.alrosa.staa.gatekeeper_perco_driver.service;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
public class PercoDriverWebSocketHandler extends TextWebSocketHandler {
    private Logger logger = Logger.getLogger(PercoDriverWebSocketHandler.class);
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Соединение установлено");
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Получено сообщение: " + message.getPayload());
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Соединение закрыто: " + status);
    }
}
