package com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects.percoc01_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects.PERCoC01;

/**
 * Класс для считывателей
 */
public class CardReader implements Global {

    //ID объекта в БД
    private int id;

    //Родительский ID объекта в БД
    private int parentId;

    //Имя объекта
    private String complete_name = "Считыватель";

    private final Direction direction = Direction.CARDREADER;

    public CardReader() {}

    public CardReader(String complete_name) {
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
    public String toString() {
        return getComplete_name();
    }
}
