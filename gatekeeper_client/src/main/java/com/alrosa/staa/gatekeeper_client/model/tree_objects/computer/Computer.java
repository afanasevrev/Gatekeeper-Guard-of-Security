package com.alrosa.staa.gatekeeper_client.model.tree_objects.computer;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для объекта "Компьютер"
 */
public class Computer implements Global {
    //Имя объекта, по умолчанию "Компьютер"
    private String complete_name = "Компьютер";

    private final Direction direction = Direction.COMPUTER;

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
