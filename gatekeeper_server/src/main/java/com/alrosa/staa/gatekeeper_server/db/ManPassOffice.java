package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для инспекторов бюро пропусков мужского пола
 */
@Setter
@Getter
@Entity
@Table(name = "gk_man_pass_office")
public class ManPassOffice {
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
    @Column(name = "parent_id")
    private int parent_id;
    public ManPassOffice(){}
    public ManPassOffice(String complete_name, String surname, String name, String patronymic, String gender, int parent_id) {
        this.complete_name = complete_name;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.parent_id = parent_id;
    }
}
