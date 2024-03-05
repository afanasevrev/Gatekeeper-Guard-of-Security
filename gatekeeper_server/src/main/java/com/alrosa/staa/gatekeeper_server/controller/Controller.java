package com.alrosa.staa.gatekeeper_server.controller;

import com.alrosa.staa.gatekeeper_server.security.Authentication;
import com.alrosa.staa.gatekeeper_server.security.LoginEventListener;
import com.alrosa.staa.gatekeeper_server.security.Roles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/")
    public String getInfo() {
        return "Система контроля и управления доступом. GateKeeper - Guard of Security";
    }

    @GetMapping("/getAuthorizedClients")
    public List<String> getAuthClients() {
        return LoginEventListener.successfulLogins;
    }

    @GetMapping("/authenticate")
    public Roles getAuthenticate() throws Exception {
        Roles roles = new Roles(LoginEventListener.getAuthorities);
        return roles;
    }
}
