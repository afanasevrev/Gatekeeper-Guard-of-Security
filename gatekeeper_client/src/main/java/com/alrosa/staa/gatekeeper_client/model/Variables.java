package com.alrosa.staa.gatekeeper_client.model;

import com.alrosa.staa.gatekeeper_client.GateKeeperClient;
import javafx.scene.image.Image;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * В классе будем хранить статические переменные и постоянные
 */
public class Variables {

    //Открываем RestTemplate
    public static RestTemplate restTemplate = new RestTemplate();

    //Указываем путь к настройкам
    public static final Properties properties = new Properties();
    //Указываем путь к рисунку shield. Значок.
    public static final Image shieldImage = new Image(GateKeeperClient.class.getResource("favicon/shield.png").toString());

    //Извлекаем настройки сервера из файла settings.properties
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/com/alrosa/staa/gatekeeper_client/settings/settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
