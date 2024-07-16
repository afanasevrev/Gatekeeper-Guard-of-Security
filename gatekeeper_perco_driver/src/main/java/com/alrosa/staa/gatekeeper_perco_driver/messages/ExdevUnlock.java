package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
/**
 * Класс, в котором будет сообщение о разблокировке ИУ
 */
@Getter
@Setter
public class ExdevUnlock {
    private int number;
    private int direction;
    private boolean unlock;
    public ExdevUnlock() {}
    public ExdevUnlock(int number, int direction, boolean unlock) {
        this.number = number;
        this.direction = direction;
        this.unlock = unlock;
    }
}
