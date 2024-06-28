package com.alrosa.staa.gatekeeper_perco_driver.controller;

import com.alrosa.staa.gatekeeper_perco_driver.commands.get_commands.GetNetworkSettings;
import com.alrosa.staa.gatekeeper_perco_driver.service.PercoDriverWebSocketClient;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
public class PercoDriverController {
    private List<String> perco_controllers = new ArrayList<>();
    private Gson gson = new Gson();
    @GetMapping("/")
    private String getConnect() {
        PercoDriverWebSocketClient myWebSocketClient = new PercoDriverWebSocketClient();
        myWebSocketClient.connect();
        GetNetworkSettings getNetworkSettings = new GetNetworkSettings();
        getNetworkSettings.setGet("net");
        String text = gson.toJson(getNetworkSettings);
        myWebSocketClient.sendMessage(text);
        return "Successful";
    }
}
