package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;

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

    @Column(name = "server_port")
    private int server_port;

    public Server(){}

    public Server(String server_name, String server_ip, int server_port) {
        this.server_name = server_name;
        this.server_ip = server_ip;
        this.server_port = server_port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public int getServer_port() {
        return server_port;
    }

    public void setServer_port(int server_port) {
        this.server_port = server_port;
    }
}
