package com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
public class Users implements Global {
    //ID объекта в БД
    private int id;
    //Родительский ID объекта в БД
    private int parentId;
    //Имя объекта по умолчанию
    private String complete_name = "Пользователи";
    private Direction direction = Direction.USERS;
    public Users() {}
    public Users(String complete_name) {
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
        return direction;
    }
    @Override
    public String toString() {
        return getComplete_name();
    }
}
