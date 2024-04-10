package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "gk_global_access_levels")
public class GlobalAccessLevels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "global_access_levels_name")
    private String global_access_levels_name;
    @Column(name = "parent_id")
    private int parent_id;
    public GlobalAccessLevels(){}
    public GlobalAccessLevels(String global_access_levels_name, int parent_id){
        this.global_access_levels_name = global_access_levels_name;
        this.parent_id = parent_id;
    }
}
