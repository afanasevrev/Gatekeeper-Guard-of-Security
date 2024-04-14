package com.alrosa.staa.gatekeeper_server.db;

import com.alrosa.staa.gatekeeper_server.messaging.Direction;
import lombok.Getter;
import lombok.Setter;
/**
 * Класс общий, для передачи сообщений клиенту
 */
@Setter
@Getter
public class General {
    private int id;
    private String complete_name;
    private Direction direction;
    private int parentId;
    public General(int parentId, Direction direction) {
        this.parentId = parentId;
        this.direction = direction;
    }
}
