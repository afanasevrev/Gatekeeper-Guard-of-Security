package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_woman_user")
public class WomanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "complete_name")
    private String complete_name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "gender")
    private String gender;
    public WomanUser(){}
    public WomanUser(String complete_name, String surname, String name, String patronymic, String gender) {
        this.complete_name = complete_name;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
    }
}
