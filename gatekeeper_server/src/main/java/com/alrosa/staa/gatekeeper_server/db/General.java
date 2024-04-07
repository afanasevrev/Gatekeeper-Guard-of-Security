package com.alrosa.staa.gatekeeper_server.db;

import com.alrosa.staa.gatekeeper_server.messaging.Direction;

public class General {
    private String complete_name;
    private int parentId;
    private Direction direction;
    private int id;

    public General(int parentId, Direction direction) {
        this.parentId = parentId;
        this.direction = direction;
    }
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getComplete_name() {
        return complete_name;
    }

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }
}
