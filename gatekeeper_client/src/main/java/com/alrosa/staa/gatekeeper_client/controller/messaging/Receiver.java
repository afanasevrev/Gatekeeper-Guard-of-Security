package com.alrosa.staa.gatekeeper_client.controller.messaging;

import com.alrosa.staa.gatekeeper_client.controller.admins_console.AdminsConsoleController;
import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.General;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.Server;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
        connectionFactory.setUsername(Variables.properties.getProperty("rabbitmq_username"));
        connectionFactory.setPassword(Variables.properties.getProperty("rabbitmq_password"));
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Variables.QUEUE_NAME_1, true, false, false, null);
        logger.info("Receiver is started");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.info("Received from the server: " + message);
            General general = null;
            try {
                general = gson.fromJson(message, General.class);
            } catch (JsonSyntaxException e) {
                logger.error("Получен неизвестный тип от сервера");
                general = new General();
            }
            switch(general.getDirection()) {
                case MAIN:
                    AdminsConsoleController.mainSystem.getValue().setComplete_name(general.getComplete_name());
                    AdminsConsoleController.mainSystem.getValue().setId(general.getId());
                    AdminsConsoleController.mainSystem.getValue().setParentId(general.getParentId());
                    AdminsConsoleController.mainSystem.getValue().setDirection(general.getDirection());
                    break;
                case SERVER:
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
                case USERS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageUsers);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case ADMINS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageAdmins);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case OPERATORS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageOperators);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case GLOBAL_ACCESS_LEVELS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageGlobalAccessLevels);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case CARDS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageCards);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case CARD_LAYOUTS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageCardLayouts);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case POSITIONS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imagePositions);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                case ORGANIZATIONS:
                    item = new TreeItem<>(general);
                    imageView = new ImageView(Variables.imageOrganizations);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    item.setGraphic(imageView);
                    Variables.adminsConsoleItem.getChildren().add(item);
                    break;
                default:
                    break;
            }
        };
        channel.basicConsume(Variables.QUEUE_NAME_1, true, deliverCallback, consumerTag -> {});
    }
}
