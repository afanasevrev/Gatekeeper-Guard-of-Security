package com.alrosa.staa.gatekeeper_server.messaging;

public enum Direction {
    //Главный объект системы
    MAIN,
    //Объекты MAIN
    BUREAU, COMPUTER, SERVER,
    //Объекты BUREAU
    USERS, ADMINS, OPERATORS, GLOBAL_ACCESS_LEVELS, CARDS, CARD_LAYOUTS, POSITIONS, ORGANIZATIONS,
    //Объекты SERVER
    PERCO,
    //Объекты PERCO
    PERCOC01,
    //Объекты PERCOC01
    CARDREADER,
    //Объекты COMPUTER
    CONSOLE,
    //Объекты USERS
    MAN_USER, WOMAN_USER,
    //Объекты ADMINS
    MAN_ADMIN, WOMAN_ADMIN,
    //Объекты OPERATORS
    MAN_OPERATOR, WOMAN_OPERATOR,
    //Объекты бюро пропусков
    PASS_OFFICE,
    //Объекты GLOBAL_ACCESS_LEVELS
    GLOBAL_ACCESS_LEVEL,
    //Объекты CARDS
    CARD,
    //Объекты CARD_LAYOUTS
    CARD_LAYOUT,
    //Объекты POSITIONS
    POSITION,
    //Объекты ORGANIZATIONS
    ORGANIZATION,
    //Объекты ORGANIZATION
    OFFICE;
}
