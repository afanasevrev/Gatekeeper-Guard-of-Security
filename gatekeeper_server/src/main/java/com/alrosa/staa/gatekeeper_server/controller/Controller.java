package com.alrosa.staa.gatekeeper_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {
    @GetMapping("/")
    public String getInfo() {
        return "Система контроля и управления доступом. GateKeeper - Guard of Security. Версия 1.0";
    }
}
