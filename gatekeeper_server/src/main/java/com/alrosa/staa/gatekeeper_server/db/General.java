package com.alrosa.staa.gatekeeper_server.db;

public class General {
    private int parentId;
    private Direction direction;

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
}
