package com.alrosa.staa.gatekeeper_perco_driver.commands.set_commands;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Exdev {
    private int number;
    private int direction;
    private String action;
    private String open_type;
    private int open_time;
    public Exdev() {}
}
