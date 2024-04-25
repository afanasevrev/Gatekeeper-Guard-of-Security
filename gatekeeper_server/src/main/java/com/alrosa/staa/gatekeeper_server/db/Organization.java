package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "organization_name")
    private String organization_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Organization() {}
    public Organization(String organization_name, int parent_id) {
        this.organization_name = organization_name;
        this.parent_id = parent_id;
    }
}
