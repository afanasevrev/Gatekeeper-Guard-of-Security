package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Объявяляем общий класс, для обмена между клиентом и сервером
 * При создании объекта, данные будем отправлять на сервер
 */
public class General implements Global {
    private int id;
    private String complete_name;
    private Direction direction;
    private int parentId;
    public General() {
        this.direction = Direction.NULL_DIRECTION;
    }
    public General(Direction direction, int parentId) {
        this.direction = direction;
        this.parentId = parentId;
    }
    public General(int id, Direction direction, int parentId, String complete_name) {
        this.id = id;
        this.direction = direction;
        this.parentId = parentId;
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
    public Direction getDirection() {
        return this.direction;
    }
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    @Override
    public String toString() {
        return getComplete_name();
    }
}
