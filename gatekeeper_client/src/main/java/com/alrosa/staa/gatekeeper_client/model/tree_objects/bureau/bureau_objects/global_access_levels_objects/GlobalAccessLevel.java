package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.global_access_levels_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.Bureau;

public class GlobalAccessLevel extends Bureau {
    //Имя объекта
    private String complete_name = "Глобальный уровень доступа";

    private final Direction direction = Direction.GLOBAL_ACCESS_LEVEL;

    public GlobalAccessLevel() {
    }

    public GlobalAccessLevel(String complete_name) {
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
