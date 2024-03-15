package com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для объекта контроллера Perco
 */
public class Perco implements Global {
    //Имя объекта
    private String complete_name = "Контроллер Perco";

    private final Direction direction = Direction.PERCO;

    public Perco() {}

    public Perco(String complete_name) {
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
