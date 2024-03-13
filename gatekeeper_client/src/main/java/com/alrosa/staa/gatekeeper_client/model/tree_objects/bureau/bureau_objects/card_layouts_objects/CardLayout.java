package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.card_layouts_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.CardLayouts;
/**
 * Класс для макета карты
 */
public class CardLayout extends CardLayouts {
    //Имя объекта
    private String complete_name = "Макет карты";

    private final Direction direction = Direction.CARD_LAYOUT;

    public CardLayout() {}

    public CardLayout(String complete_name) {
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
