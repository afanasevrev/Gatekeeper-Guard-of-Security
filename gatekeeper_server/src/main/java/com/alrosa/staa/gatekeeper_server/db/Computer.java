package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gk_computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "computer_name")
    private String computer_name;
    @Column(name = "computer_ip")
    private String computer_ip;
    @Column(name = "parent_id")
    private int parent_id;
    public Computer(){}
    public Computer(String computer_name, String computer_ip, int parent_id) {}
}
