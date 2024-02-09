package com.alrosa.staa.gatekeeper_server.controller;

import com.alrosa.staa.gatekeeper_server.security.Authentication;
import com.alrosa.staa.gatekeeper_server.security.LoginEventListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/")
    public String getInfo() {
        return "Система контроля и управления доступом. GateKeeper - Guard of Security. Версия 1.0";
    }

    @GetMapping("/getAuth")
    public String getAuthentication() {
        return Authentication.AUTHENTICATION + ":" + Authentication.ADMIN;
    }

    @GetMapping("/getAuthorizedClients")
    public List<String> getAuthClients() {
        return LoginEventListener.successfulLogins;
    }

}
