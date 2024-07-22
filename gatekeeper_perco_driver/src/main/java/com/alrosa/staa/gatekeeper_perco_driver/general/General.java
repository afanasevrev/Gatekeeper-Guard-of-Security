package com.alrosa.staa.gatekeeper_perco_driver.general;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class General {
    private MessageType messageType;
    private String card_identifier;
    public General(){}
    public General(MessageType messageType, String card_identifier) {
        this.card_identifier = card_identifier;
        this.messageType = messageType;
    }
}
