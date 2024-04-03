package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

/**
 * Интерфейс для объектов дерева
 */
public interface Global {
    /**
     * Присваивает ID объекту
     */
    void setId(int id);
    /**
     * Присваивает родительское ID объекту
     */
    void setParentId(int parentId);
    /**
     * Возвращает ID объекта
     * @return
     */
    int getId();
    /**
     * Возвращает родительское ID объекта
     * @return
     */
    int getParentId();
    /**
     * Возвращает имя объекта
     * @return
     */
    String getComplete_name();
    /**
     * Присваивает имя объекту
     * @param complete_name
     */
    void setComplete_name(String complete_name);
    /**
     * Возвращаем enum объекта
     * @return
     */
    Direction getDirection();
    /**
     * Назначаем enum объекта
     */
    void setDirection(Direction direction);
}
