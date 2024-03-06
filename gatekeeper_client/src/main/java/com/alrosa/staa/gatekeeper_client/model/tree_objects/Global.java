package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Класс предназначен для объектов дерева
 */
public abstract class Global {
    //Наименование объекта
    private String complete_name;

    //Enum возвращаемого объекта
    private Direction direction;

    /**
     *
     * @return возвращает имя объекта в дереве
     */
    public abstract String getComplete_name();

    /**
     * Присваивает имя объекту
     * @param complete_name
     */
    public abstract void setComplete_name(String complete_name);

    /**
     *
     * @return возвращет enum объекта
     */
    public abstract Direction getDirection();

    /**
     * Присваивает enum объекту
     * @param direction
     */
    public abstract void setDirection(Direction direction);
}
