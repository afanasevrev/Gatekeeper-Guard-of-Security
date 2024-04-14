package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для организаций
 */
@Setter
@Getter
@Entity
@Table(name = "gk_organizations")
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "organizations_name")
    private String organizations_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Organizations(){}
    public Organizations(String organizations_name, int parent_id) {
        this.organizations_name = organizations_name;
        this.parent_id = parent_id;
    }
}
