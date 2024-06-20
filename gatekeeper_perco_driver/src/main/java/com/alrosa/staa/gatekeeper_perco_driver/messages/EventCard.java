package com.alrosa.staa.gatekeeper_perco_driver.messages;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EventCard {
    private Card card;
    private String event;
    public EventCard() {}
    public EventCard(Card card, String event) {
        this.card = card;
        this.event = event;
    }
}
