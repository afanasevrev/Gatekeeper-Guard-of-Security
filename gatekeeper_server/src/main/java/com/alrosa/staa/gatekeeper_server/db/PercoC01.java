package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для контроллера PERCo-C01
 */
@Setter
@Getter
@Entity
@Table(name = "gk_percoc01")
public class PercoC01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "percoc01_name")
    private String percoc01_name;
    @Column(name = "ip_address")
    private String ip_address;
    @Column(name = "status")
    private boolean status;
    @Column(name = "parent_id")
    private int parent_id;
    public PercoC01(){}
    public PercoC01(String percoc01_name, String ip_address, boolean status, int parent_id) {
        this.percoc01_name = percoc01_name;
        this.ip_address = ip_address;
        this.status = status;
        this.parent_id = parent_id;
    }
}
