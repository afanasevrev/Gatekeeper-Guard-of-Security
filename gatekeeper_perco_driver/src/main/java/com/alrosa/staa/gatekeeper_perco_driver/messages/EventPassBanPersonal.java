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
public class EventPassBanPersonal {
    private PassBanPersonal pass_ban_personal;
    private String event;
    public EventPassBanPersonal() {}
    public EventPassBanPersonal(PassBanPersonal pass_ban_personal, String event) {
        this.pass_ban_personal = pass_ban_personal;
        this.event = event;
    }
}

