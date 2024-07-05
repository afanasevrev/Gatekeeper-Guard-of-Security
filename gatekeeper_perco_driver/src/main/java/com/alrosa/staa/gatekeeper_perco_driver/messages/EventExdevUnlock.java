package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EventExdevUnlock {
    private ExdevUnlock exdev_unlock;
    private String event;
    public EventExdevUnlock() {}
    public EventExdevUnlock(ExdevUnlock exdev_unlock, String event) {
        this.exdev_unlock = exdev_unlock;
        this.event = event;
    }
}
