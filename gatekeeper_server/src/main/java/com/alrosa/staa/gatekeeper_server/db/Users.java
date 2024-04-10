package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "gk_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "users_name")
    private String users_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Users(){}
    public Users(String users_name, int parent_id){
        this.users_name = users_name;
        this.parent_id = parent_id;
    }
}
