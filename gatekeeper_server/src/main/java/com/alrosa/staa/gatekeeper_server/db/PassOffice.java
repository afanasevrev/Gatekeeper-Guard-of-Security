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
@Table(name = "gk_pass_office")
public class PassOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "pass_office_name")
    private String pass_office_name;
    @Column(name = "parent_id")
    private int parent_id;
    public PassOffice(){}
    public PassOffice(String pass_office_name, int parent_id) {
        this.pass_office_name = pass_office_name;
        this.parent_id = parent_id;
    }
}
