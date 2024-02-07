package com.alrosa.staa.gatekeeper_server.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс нужен для фиксации логинов авторизованных клиентов
 */
@Component
public class LoginEventListener {
    public static List<String> successfulLogins = new ArrayList<>();

    @EventListener
    public void handleSuccessfulLogin(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();

        successfulLogins.add(username);
        System.out.println("Successful login for user: " + username);
        System.out.println( event.getAuthentication().getDetails());
    }

    public List<String> getSuccessfulLogins() {
        return successfulLogins;
    }
}
