package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для глобального уровня доступа
 */
@Setter
@Getter
@Entity
@Table(name = "gk_global_access_level")
public class GlobalAccessLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "global_access_level_name")
    private String global_access_level_name;
    @Column(name = "parent_id")
    private int parent_id;
    public GlobalAccessLevel(){}
    public GlobalAccessLevel(String global_access_level_name, int parent_id) {
        this.global_access_level_name = global_access_level_name;
        this.parent_id = parent_id;
    }
}
