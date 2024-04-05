package com.alrosa.staa.gatekeeper_client.controller.messaging;

import com.alrosa.staa.gatekeeper_client.controller.admins_console.AdminsConsoleController;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.General;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.Server;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Класс Приёмник сообщений от сервера
 * Класс реализуем по паттерну Singleton
 */
public class Receiver {
    Logger logger = Logger.getLogger(Receiver.class);
    private static Receiver INSTANCE;
    private Gson gson = new Gson();
    private TreeItem<Global> item;
    private ImageView imageView;
    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private Receiver(){}
    public synchronized static Receiver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Receiver();
        }
        return INSTANCE;
    }
    /**
     * Метод запускает приёмник сообщений
     */
    public void start() throws IOException, TimeoutException {
        connectionFactory.setHost(Variables.properties.getProperty("rabbitmq_ip"));
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Variables.QUEUE_NAME_1, true, false, false, null);
        logger.info("Receiver is started");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.info("Received from the server: " + message);
            General general = gson.fromJson(message, General.class);
            switch(general.getDirection()) {
                case MAIN:
                    AdminsConsoleController.mainSystem.getValue().setComplete_name(general.getComplete_name());
                    AdminsConsoleController.mainSystem.getValue().setId(general.getId());
                    AdminsConsoleController.mainSystem.getValue().setParentId(general.getParentId());
                    AdminsConsoleController.mainSystem.getValue().setDirection(general.getDirection());
                    break;
                case SERVER:
                    //Server server = new Server();
                    //server.setComplete_name(general.getComplete_name());
                    //server.setDirection(general.getDirection());
                    //server.setId(general.getId());
                    //server.setParentId(general.getParentId());
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageServer);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case BUREAU:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageBureau);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case COMPUTER:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageComputer);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                default:
                    logger.info("Получен неизвестный тип");
                    break;
            }
        };
        channel.basicConsume(Variables.QUEUE_NAME_1, true, deliverCallback, consumerTag -> {});
    }
}
