package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс сущности администратора системы
 */
@Entity
@Table(name = "gk_admins")
@Getter
@Setter
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "admins_name")
    private String admins_name;

    @Column(name = "parent_id")
    private int parent_id;
    public Admins(){}
    public Admins(String admins_name, int parent_id) {
        this.admins_name = admins_name;
        this.parent_id = parent_id;
    }
}
