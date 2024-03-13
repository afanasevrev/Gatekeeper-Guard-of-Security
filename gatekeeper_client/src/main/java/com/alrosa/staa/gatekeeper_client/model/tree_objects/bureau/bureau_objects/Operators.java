package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.Bureau;

/**
 * Класс для операторов
 */
public class Operators extends Bureau {
    //Имя объекта
    private String complete_name = "Операторы";

    private final Direction direction = Direction.OPERATORS;

    public Operators() {}

    public Operators(String complete_name) {
        this.complete_name = complete_name;
    }

    @Override
    public String getComplete_name() {
        return complete_name;
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
