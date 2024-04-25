package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для должности
 */
@Setter
@Getter
@Entity
@Table(name = "gk_position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "position_name")
    private String position_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Position() {}
    public Position(String position_name, int parent_id) {
        this.position_name = position_name;
        this.parent_id = parent_id;
    }
}
