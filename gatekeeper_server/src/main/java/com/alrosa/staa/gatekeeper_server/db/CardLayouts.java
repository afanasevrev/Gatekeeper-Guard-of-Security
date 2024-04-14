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
@Table(name = "gk_card_layouts")
public class CardLayouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "card_layouts_name")
    private String card_layouts_name;
    @Column(name = "parent_id")
    private int parent_id;
    public CardLayouts(){}
    public CardLayouts(String card_layouts_name, int parent_id) {
        this.card_layouts_name = card_layouts_name;
        this.parent_id = parent_id;
    }
}
