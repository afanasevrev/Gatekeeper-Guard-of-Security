package com.alrosa.staa.gatekeeper_server.db;

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
    //Объекты USERS, ADMINS, OPERATORS
    MAN, WOMAN,
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
