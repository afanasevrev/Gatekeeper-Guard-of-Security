package com.alrosa.staa.gatekeeper_client.model;

import com.alrosa.staa.gatekeeper_client.GateKeeperClient;
import javafx.scene.image.Image;

/**
 * В классе будем хранить статические переменные
 */
public class Variables {
    //Указываем путь к рисунку shield. Значок.
    public static final Image shieldImage = new Image(GateKeeperClient.class.getResource("favicon/shield.png").toString());

}
