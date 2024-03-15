package com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для контроллера PERCoC01
 */
public class PERCoC01 implements Global {
    //Имя объекта
    private String complete_name = "Контроллер PERC0-C01";

    private final Direction direction = Direction.PERCOC01;

    public PERCoC01() {}

    public PERCoC01(String complete_name) {
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
