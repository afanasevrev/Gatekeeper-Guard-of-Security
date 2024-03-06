package com.alrosa.staa.gatekeeper_client.model.tree_objects;

import com.alrosa.staa.gatekeeper_client.model.Direction;

public interface GlobalInterface {
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
}
