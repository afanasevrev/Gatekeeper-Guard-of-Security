package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_console")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "console_name")
    private String console_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Console(){}
    public Console(String console_name, int parent_id) {
        this.console_name = console_name;
        this.parent_id = parent_id;
    }
}
