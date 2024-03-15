package com.alrosa.staa.gatekeeper_client.model.tree_objects.computer.computer_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.computer.Computer;

/**
 * Класс для объекта "Консоль"
 */
public class Console implements Global {
    //Имя объекта
    private String complete_name = "Консоль";

    private Direction direction = Direction.CONSOLE;

    public Console(){}

    public Console(String complete_name) {
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
