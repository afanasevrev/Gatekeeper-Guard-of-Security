package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Класс для главной вершины дерева
 */
public class Main implements Global {

    //ID объекта в БД
    private int id;

    //Родительский ID объекта в БД
    private int parentId;

    //Имя объекта
    private String complete_name = "Главный";

    private final Direction direction = Direction.MAIN;

    public Main(){}

    public Main(String complete_name) {
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
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        return getComplete_name();
    }
}
