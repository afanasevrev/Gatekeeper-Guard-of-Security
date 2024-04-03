package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Объявяляем общий класс, для обмена между клиентом и сервером
 * При создании объекта, данные будем отправлять на сервер
 */
public class General implements Global {

    private Direction direction;

    private int parentId;

    public General(Direction direction, int parentId) {
        this.direction = direction;
        this.parentId = parentId;
    }

    @Override
    public void setId(int id) {}

    @Override
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getParentId() {
        return this.parentId;
    }

    @Override
    public String getComplete_name() {
        return null;
    }

    @Override
    public void setComplete_name(String complete_name) {}

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
