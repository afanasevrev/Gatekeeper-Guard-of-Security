package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.Bureau;

/**
 * Класс для глобальных уровней доступа
 */
public class GlobalAccessLevels extends Bureau {
    //Имя объекта
    private String complete_name = "Глобальные уровни доступа";

    private final Direction direction = Direction.GLOBAL_ACCESS_LEVELS;

    public GlobalAccessLevels() {}

    public GlobalAccessLevels(String complete_name) {
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
