package com.alrosa.staa.gatekeeper_server.security;

public class Roles {
    private String role;

    public Roles(){}

    public Roles(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
