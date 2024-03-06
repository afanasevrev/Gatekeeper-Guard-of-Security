package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Класс для корня дерева
 */
public class Main {
    //Имя объекта
    private String complete_name;

    private Direction direction = Direction.MAIN;

    public Main(){}
    public Main(String complete_name) {
        this.complete_name = complete_name;
    }


    public String getComplete_name() {
        return complete_name;
    }


    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }


    public Direction getDirection() {
        return this.direction;
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
