package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.organizations_objects.organization_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для отдела
 */
public class Office implements Global {
    //Имя объекта
    private String complete_name = "Отдел";

    private final Direction direction = Direction.OFFICE;

    public Office() {}

    public Office(String complete_name) {
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
