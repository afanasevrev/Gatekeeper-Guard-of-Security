package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Card {
    private int number;
    private int direction;
    private String id;
    private boolean remove_card;
    public Card() {}
    public Card(int number, int direction, String id, boolean remove_card) {
        this.number = number;
        this.direction = direction;
        this.id = id;
        this.remove_card = remove_card;
    }
}
