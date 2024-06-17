package com.alrosa.staa.gatekeeper_perco_driver.controller;

import com.alrosa.staa.gatekeeper_perco_driver.service.MyWebSocketClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebSocketController {
    @GetMapping("/")
    private String getConnect() {
        MyWebSocketClient myWebSocketClient = new MyWebSocketClient();
        myWebSocketClient.connect();
        myWebSocketClient.sendMessage("{\n" +
                "\"get\" : \"net\",\n" +
                "}");
        return "Successful";
    }
}
