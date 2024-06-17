package com.alrosa.staa.gatekeeper_perco_driver.controller;

import com.alrosa.staa.gatekeeper_perco_driver.commands.receive.GetNetworkSettings;
import com.alrosa.staa.gatekeeper_perco_driver.service.MyWebSocketClient;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebSocketController {
    private Gson gson = new Gson();
    @GetMapping("/")
    private String getConnect() {
        MyWebSocketClient myWebSocketClient = new MyWebSocketClient();
        myWebSocketClient.connect();
        GetNetworkSettings getNetworkSettings = new GetNetworkSettings();
        getNetworkSettings.setGet("state");
        String text = gson.toJson(getNetworkSettings);
        myWebSocketClient.sendMessage(text);
        return "Successful";
    }
}
