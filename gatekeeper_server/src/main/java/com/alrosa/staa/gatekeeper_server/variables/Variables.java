package com.alrosa.staa.gatekeeper_server.variables;


import java.util.Date;

/**
 * В классе будем хранить переменные, в том числе и статические
 */
public class Variables {
    //Переменная для сеанса связи по RabbitMQ
    public static final String QUEUE_NAME = "Gatekeeper";

    public static final String QUEUE_NAME_1 = "Gatekeeper_1";

    public static Date date = new Date();
}
