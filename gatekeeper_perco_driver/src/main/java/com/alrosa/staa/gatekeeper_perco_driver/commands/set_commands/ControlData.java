package com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ControlData {
    private String control;
    private Exdev exdev;
    public ControlData() {}
}
