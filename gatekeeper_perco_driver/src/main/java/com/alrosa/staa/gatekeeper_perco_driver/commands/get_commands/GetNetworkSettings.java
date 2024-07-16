package com.alrosa.staa.gatekeeper_perco_driver.commands.get_commands;

import lombok.Getter;
import lombok.Setter;
/**
 * Класс для отпраки на контроллер команды и в ответ получение сетевых настроек
 */
@Getter
@Setter
public class GetNetworkSettings {
    private String get;
    public  GetNetworkSettings() {}
    public GetNetworkSettings(String get) {
        this.get = get;
    }
}