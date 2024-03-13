package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.Bureau;

/**
 * Класс для администраторов
 */
public class Admins extends Bureau {
    //Имя объекта
    private String complete_name = "Администраторы";

    private Direction direction = Direction.ADMINS;
    public Admins() {}

    public Admins(String complete_name) {
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
