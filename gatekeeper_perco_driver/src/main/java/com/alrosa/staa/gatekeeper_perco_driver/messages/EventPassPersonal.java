package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
/**
 * Класс предназначен для получения от контроллера сообщения
 * о том, что приложенная карта имеет доступ к данному
 * исполнительному устройству
 */
@Getter
@Setter
public class EventPassPersonal {
    private PassPersonal pass_personal;
    private String event;
    public EventPassPersonal() {}
    public EventPassPersonal(PassPersonal pass_personal, String event) {
        this.pass_personal = pass_personal;
        this.event = event;
    }
}
