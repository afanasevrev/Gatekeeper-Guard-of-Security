package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;

/**
 * Класс сущности для бюро пропусков
 */

@Entity
@Table(name = "gk_bureau")
public class Bureau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bureau_name")
    private String bureau_name;

    @Column(name = "parent_id")
    private int parent_id;

    public Bureau(){}

    public Bureau(int parentId, String bureau_name) {
        this.id = id;
        this.bureau_name = bureau_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
