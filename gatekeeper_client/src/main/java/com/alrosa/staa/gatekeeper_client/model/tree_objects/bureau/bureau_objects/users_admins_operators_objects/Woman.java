package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.users_admins_operators_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для отображения человека женского пола
 */
public class Woman implements Global {
    //Имя объекта
    private String complete_name = "Человек";

    private final Direction direction = Direction.WOMAN;

    public Woman() {}

    public Woman(String complete_name) {
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
