package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для отдела
 */
@Setter
@Getter
@Entity
@Table(name = "gk_office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "office_name")
    private String office_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Office(){}
    public Office(String office_name, int parent_id) {
        this.office_name = office_name;
        this.parent_id = parent_id;
    }
}
