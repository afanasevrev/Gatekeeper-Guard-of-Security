package com.alrosa.staa.gatekeeper_perco_driver.commands.receive;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GetNetworkSettings {
    private String get;
    public  GetNetworkSettings() {}
    public GetNetworkSettings(String get) {
        this.get = get;
    }
}