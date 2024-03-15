package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

public class Users implements Global {
    //Имя объекта по умолчанию
    private String complete_name = "Пользователи";

    private final Direction direction = Direction.USERS;
    public Users() {}

    public Users(String complete_name) {
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
        return direction;
    }

    @Override
    public String toString() {
        return getComplete_name();
    }
}
