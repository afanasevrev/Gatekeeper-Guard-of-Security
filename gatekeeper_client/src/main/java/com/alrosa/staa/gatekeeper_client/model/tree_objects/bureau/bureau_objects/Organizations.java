package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;

/**
 * Класс для организаций
 */
public class Organizations implements Global {
    //ID объекта в БД
    private int id;
    //Родительский ID объекта в БД
    private int parentId;
    //Имя объекта
    private String complete_name = "Организации";
    private Direction direction = Direction.ORGANIZATIONS;
    public Organizations() {}
    public Organizations(String complete_name) {
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
        return this.complete_name;
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
