package com.alrosa.staa.gatekeeper_perco_driver.general;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class General {
    //Тип сообщения, адресуем оператору или админу
    private MessageType messageType;
    //Номер карты
    private String cardId;
    //IP - адрес контроллера
    private String ipAddress;
    //Направление вход/выход
    private String direction;
    public General(){}
    public General(MessageType messageType, String cardId, String ipAddress, String direction) {
        this.messageType = messageType;
        this.cardId = cardId;
        this.ipAddress = ipAddress;
        this.direction = direction;
    }
}
