package com.alrosa.staa.gatekeeper_client.model;

import com.alrosa.staa.gatekeeper_client.controller.sessions.Transceiver;

public interface Sender {

    public Transceiver send();
}
