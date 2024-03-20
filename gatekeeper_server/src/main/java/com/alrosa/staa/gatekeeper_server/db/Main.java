package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;

/**
 * Класс сущности для главного объекта в дереве системы
 */
@Entity
@Table(name = "gk_main")
public class Main {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Main() {}

    public Main(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
