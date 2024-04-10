package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_positions")
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private int id;
    @Column(name = "positions_name")
    private String positions_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Positions(){}
    public Positions(String positions_name, int parent_id) {
        this.positions_name = positions_name;
        this.parent_id = parent_id;
    }
}
