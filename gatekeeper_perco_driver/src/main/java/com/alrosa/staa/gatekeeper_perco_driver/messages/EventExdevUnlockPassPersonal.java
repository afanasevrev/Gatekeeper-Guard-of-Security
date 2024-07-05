package com.alrosa.staa.gatekeeper_perco_driver.messages;

public class EventExdevUnlockPassPersonal {
    private EventExdevUnlock eventExdevUnlock;
    private EventPassPersonal eventPassPersonal;
    public EventExdevUnlockPassPersonal() {}
    public EventExdevUnlockPassPersonal(EventExdevUnlock eventExdevUnlock, EventPassPersonal eventPassPersonal) {
        this.eventExdevUnlock = eventExdevUnlock;
        this.eventPassPersonal = eventPassPersonal;
    }
}
