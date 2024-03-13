package com.alrosa.staa.gatekeeper_client.model;

import com.alrosa.staa.gatekeeper_client.GateKeeperClient;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * В классе будем хранить статические переменные и постоянные
 */
public class Variables {
    //Постоянная для сессий на основе RabbitMQ
    public static final String QUEUE_NAME = "Gatekeeper";
    //Сюда запишем полученный SessionID
    public static String jSessionId;
    //Указываем путь к настройкам
    public static final Properties properties = new Properties();
    //Добавляем вершину дерева для контейнера
    public static TreeItem<Global> containerConsoleItem;
    //Добавляем вершину дерева для админского консоля
    public static TreeItem<Global> adminsConsoleItem;
    //Добавляем статический enum для контейнера
    public static Direction containerConsoleDirection = Direction.MAIN;
    //Добавляем статический enum для админского консоля
    public static Direction adminsConsoleDirection = Direction.MAIN;
    //Указываем путь к рисунку shield. Значок.
    public static final Image shieldImage = new Image(GateKeeperClient.class.getResource("favicon/shield.png").toString());
    //Указываем путь к рисунку main.
    public static final Image mainImage = new Image(GateKeeperClient.class.getResource("icons/main.png").toString());
    //Извлекаем настройки сервера из файла settings.properties
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/com/alrosa/staa/gatekeeper_client/settings/settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
