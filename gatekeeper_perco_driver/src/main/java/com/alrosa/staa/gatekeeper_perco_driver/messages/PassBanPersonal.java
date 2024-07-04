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
    private boolean remove_card;
    private String command_source;
    public PassBanPersonal() {}
    public PassBanPersonal(int number, int direction, String id, boolean remove_card, String command_source) {
        this.number = number;
        this.direction = direction;
        this.id = id;
        this.remove_card = remove_card;
        this.command_source = command_source;
    }
}
