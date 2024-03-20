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

    private final Direction direction = Direction.MAIN;

    public Main() {}

    public Main(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
