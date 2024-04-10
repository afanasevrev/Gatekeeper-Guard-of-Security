package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс сущности для главного объекта в дереве системы
 */
@Setter
@Getter
@Entity
@Table(name = "gk_main")
public class Main {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String complete_name;
    public Main() {}
    public Main(String name) {
        this.complete_name = name;
    }
}
