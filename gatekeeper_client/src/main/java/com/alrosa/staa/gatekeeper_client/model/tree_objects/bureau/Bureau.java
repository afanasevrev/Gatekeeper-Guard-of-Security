package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для объектов "Бюро"
 */
public class Bureau implements Global {
    //Имя объекта, по умолчанию "Бюро"
    private String complete_name = "Бюро";

    private final Direction direction = Direction.BUREAU;

    public Bureau(){}

    public Bureau(String complete_name) {
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
