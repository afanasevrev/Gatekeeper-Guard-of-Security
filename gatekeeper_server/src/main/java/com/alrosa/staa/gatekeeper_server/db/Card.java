package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для карт доступа
 */
@Setter
@Getter
@Entity
@Table(name = "gk_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_name")
    private String card_name;
    @Column(name = "card_id")
    private long card_id;
    @Column(name = "parent_id")
    private int parent_id;
    public Card(){}
    public Card(String card_name, long card_id, int parent_id) {
        this.card_name = card_name;
        this.card_id = card_id;
        this.parent_id = parent_id;
    }
}
