package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для контроллеров Perco
 */
@Setter
@Getter
@Entity
@Table(name = "gk_perco")
public class Perco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column
    private String perco_name;
    @Column
    private int parent_id;
    public Perco(){}
    public Perco(String perco_name, int parent_id){
        this.perco_name = perco_name;
        this.parent_id = parent_id;
    }
}
