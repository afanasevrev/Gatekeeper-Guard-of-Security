package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.cards_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.Cards;

/**
 * Класс для карты доступа
 */
public class Card extends Cards {
    //Имя объекта
    private String complete_name = "Карта доступа";

    private final Direction direction = Direction.CARD;

    public Card() {}

    public Card(String complete_name) {
        this.complete_name = complete_name;
    }

    @Override
    public String getComplete_name() {
        return this.complete_name;
    }

    @Override
    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        return getComplete_name();
    }
}
