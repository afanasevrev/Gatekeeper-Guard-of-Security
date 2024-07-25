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
    private int direction;
    //Доступ
    private boolean access;
    //Текущая дата
    private String currentDate;
    public General(){}
    public General(MessageType messageType, String currentDate, String cardId, String ipAddress, int direction, boolean access) {
        this.messageType = messageType;
        this.currentDate = currentDate;
        this.cardId = cardId;
        this.ipAddress = ipAddress;
        this.direction = direction;
        this.access = access;
    }
}
