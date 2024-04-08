package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс сущности для дежурного оператора
 */
@Entity
@Table(name = "gk_operators")
@Getter
@Setter
public class Operators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "operators_name")
    private String operators_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Operators(){}
    public Operators(String operators_name, int parent_id) {
        this.operators_name = operators_name;
        this.parent_id = parent_id;
    }

}
