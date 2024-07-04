package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
/**
 * Класс предназначен для получения от контроллера сообщения
 * о том, что приложенная карта не имеет доступа к данному
 * исполнительному устройству
 */
@Getter
@Setter
public class PassBanPersonal {
    private int number;
    private int direction;
    private String id;
    private boolean removeCard;
    private String commandSource;
    public PassBanPersonal() {}
    public PassBanPersonal(int number, int direction, String id, boolean removeCard, String commandSource) {
        this.number = number;
        this.direction = direction;
        this.id = id;
        this.removeCard = removeCard;
        this.commandSource = commandSource;
    }
}
