package com.alrosa.staa.gatekeeper_client.model;

import lombok.Setter;
import lombok.Getter;
/**
 * Класс для приёма роли от сервера
 */
@Setter
@Getter
public class Roles {
    private String role;
    public Roles(){}
    public Roles(String role) {
        this.role = role;
    }
}
