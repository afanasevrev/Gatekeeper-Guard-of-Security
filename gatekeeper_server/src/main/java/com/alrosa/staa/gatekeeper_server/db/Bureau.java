package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;

/**
 * Класс сущности для бюро пропусков
 */

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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBureau_name() {
        return bureau_name;
    }
    public void setBureau_name(String bureau_name) {
        this.bureau_name = bureau_name;
    }
    public int getParent_id() {
        return parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
