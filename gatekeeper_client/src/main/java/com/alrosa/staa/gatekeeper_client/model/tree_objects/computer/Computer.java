package com.alrosa.staa.gatekeeper_client.model.tree_objects.computer;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для объекта "Компьютер"
 */
public class Computer implements Global {

    //ID объекта в БД
    private int id;

    //Родительский ID объекта в БД
    private int parentId;

    //Имя объекта, по умолчанию "Компьютер"
    private String complete_name = "Компьютер";

    private Direction direction = Direction.COMPUTER;

    public Computer(){}

    public Computer(String complete_name) {
        this.complete_name = complete_name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getParentId() {
        return this.parentId;
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
    public void setDirection(Direction direction) {
        this.direction = direction;
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
