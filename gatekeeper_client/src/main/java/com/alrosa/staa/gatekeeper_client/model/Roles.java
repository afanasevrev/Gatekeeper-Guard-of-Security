package com.alrosa.staa.gatekeeper_client.model;

/**
 * Класс предназначен для приёма роли от сервера
 */
public class Roles {
    private String role;

    public Roles(){}

    public Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
