package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для серверов
 */
@Setter
@Getter
@Entity
@Table(name = "gk_servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "server_name")
    private String server_name;
    @Column(name = "server_ip")
    private String server_ip;
    @Column(name = "parent_id")
    private int parent_id;
    public Server(){}
    public Server(String server_name, String server_ip, int parent_id) {
        this.server_name = server_name;
        this.server_ip = server_ip;
        this.parent_id = parent_id;
    }
}
