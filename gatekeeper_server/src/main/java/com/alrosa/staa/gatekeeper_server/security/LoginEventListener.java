package com.alrosa.staa.gatekeeper_server.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс нужен для фиксации авторизованных клиентов
 */
@Component
public class LoginEventListener {
    //Массив для хранения логинов
    public static List<String> successfulLogins = new ArrayList<>();
    //Записываем роль подключенного клиента
    public static String getAuthorities;

    @EventListener
    public void handleSuccessfulLogin(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        successfulLogins.add(username);
        System.out.println(event.getAuthentication().getDetails());
        getAuthorities = String.valueOf(event.getAuthentication().getAuthorities());
    }
}
