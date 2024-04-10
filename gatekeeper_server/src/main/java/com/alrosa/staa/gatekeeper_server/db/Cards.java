package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gk_cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cards_name")
    private String cards_name;
    @Column(name = "parent_id")
    private int parent_id;
    public Cards(){}
    public Cards(String cards_name, int parent_id) {
        this.cards_name = cards_name;
        this.parent_id = parent_id;
    }
}
