package com.alrosa.staa.gatekeeper_server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс для макетов карт
 */
@Setter
@Getter
@Entity
@Table(name = "gk_card_layout")
public class CardLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_layout_name")
    private String card_layout_name;
    @Column(name = "parent_id")
    private int parent_id;
    public CardLayout(){}
    public CardLayout(String card_layout_name, int parent_id) {
        this.card_layout_name = card_layout_name;
        this.parent_id = parent_id;
    }
}
