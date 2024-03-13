package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.positions_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.Positions;

/**
 * Класс для должности
 */
public class Position extends Positions {
    //Имя объекта
    private String complete_name = "Должность";

    private final Direction direction = Direction.POSITION;

    public Position() {}

    public Position(String complete_name) {
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
