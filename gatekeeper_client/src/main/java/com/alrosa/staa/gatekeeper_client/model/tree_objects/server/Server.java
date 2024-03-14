package com.alrosa.staa.gatekeeper_client.model.tree_objects.server;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;

/**
 * Класс для объекта "Сервер"
 */
public class Server implements Global {
    //Имя объекта, по умолчанию "Сервер"
    private String complete_name = "Сервер";
    private final Direction direction = Direction.SERVER;

    public Server(){}

    public Server(String complete_name) {
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
