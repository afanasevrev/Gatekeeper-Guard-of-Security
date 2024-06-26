package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для бюро пропусков
 */
@Setter
@Getter
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
    public Bureau(String bureau_name, int parent_id) {
        this.bureau_name = bureau_name;
        this.parent_id = parent_id;
    }
}
