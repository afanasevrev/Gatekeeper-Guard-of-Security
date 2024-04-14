package com.alrosa.staa.gatekeeper_server.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Требуется для передачи клиенту роли авторизованного юзера
 */
@Setter
@Getter
public class Roles {
    private String role;
    public Roles(){}
    public Roles(String role){
        this.role = role;
    }
}
