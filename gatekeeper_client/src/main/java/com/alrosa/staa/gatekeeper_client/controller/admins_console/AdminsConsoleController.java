package com.alrosa.staa.gatekeeper_client.controller.admins_console;

import com.alrosa.staa.gatekeeper_client.controller.messaging.Receiver;
import com.alrosa.staa.gatekeeper_client.controller.messaging.Transmitter;
import com.alrosa.staa.gatekeeper_client.model.CommandList;
import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import com.alrosa.staa.gatekeeper_client.view.Container;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * Контроллер для работы с файлом admins_console.fxml
 */
public class AdminsConsoleController implements Initializable {
    //Создаем экземпляр класса Receiver
    private Receiver receiver = Receiver.getInstance();

    //Создаем экземпляр класса Transmitter
    private Transmitter transmitter = Transmitter.getInstance();

    //Задействуем JSON
    private Gson gson = new Gson();

    //Текст для отправки на сервер
    private String text = new String();

    //Создаем экземпляр класса Container
    Container container = new Container();
    //Создаем сцену
    Stage stage = new Stage();
    //Добавим контекстное меню
    private ContextMenu contextMenu = new ContextMenu();
    //Создание кнопки "Добавить"
    private MenuItem menuAdd = new MenuItem("Добавить");
    //Создание кнопки "Удалить"
    private MenuItem menuDelete = new MenuItem("Удалить");
    //Окно для отображения дерева
    @FXML
    private AnchorPane windowTree = new AnchorPane();
    //Горизонтальный сплиттер
    @FXML
    private SplitPane horizontal = new SplitPane();
    //Вертикальный сплиттер
    @FXML
    private SplitPane vertical = new SplitPane();
    //Вертикальный бокс
    @FXML
    private VBox vBox = new VBox();
    //Создаем главную вершину дерева
    private static TreeItem<Global> mainSystem = new TreeItem<>(new Main("Главный"));
    //Создаем само дерево
    private static TreeView treeView = new TreeView(mainSystem);
    //Создаем ссылку на рисунок для главной вершины
    private final ImageView mainView = new ImageView(Variables.mainImage);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //В контекстное меню добавляем кнопки
        contextMenu.getItems().addAll(menuAdd, menuDelete);
        //В наше дерево добавляем контекстное меню
        treeView.setContextMenu(contextMenu);

        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);

        AnchorPane.setLeftAnchor(horizontal, 0.0);
        AnchorPane.setBottomAnchor(horizontal, 0.0);
        AnchorPane.setTopAnchor(horizontal, 0.0);
        AnchorPane.setRightAnchor(horizontal, 0.0);

        AnchorPane.setLeftAnchor(vertical, 0.0);
        AnchorPane.setBottomAnchor(vertical, 0.0);
        AnchorPane.setTopAnchor(vertical, 0.0);
        AnchorPane.setRightAnchor(vertical, 0.0);

        AnchorPane.setLeftAnchor(treeView, 0.0);
        AnchorPane.setBottomAnchor(treeView, 0.0);
        AnchorPane.setTopAnchor(treeView, 0.0);
        AnchorPane.setRightAnchor(treeView, 0.0);

        windowTree.getChildren().add(treeView);
        mainView.setFitWidth(25);
        mainView.setFitHeight(25);
        mainSystem.setGraphic(mainView);

        //Запускаем слушатель сообщений от сервера
        try {
            receiver.start();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        //Отправляем сообщение на сервер
        try {
            text = gson.toJson(mainSystem.getValue());
            transmitter.sendMessage(text);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        //Добавляем реакцию на нажатие вершины дерева
        treeView.setOnMouseClicked(event -> {
                    Variables.adminsConsoleItem = (TreeItem<Global>) treeView.getSelectionModel().getSelectedItem();
                    Variables.adminsConsoleDirection = Variables.adminsConsoleItem.getValue().getDirection();
                    Variables.parentId = Variables.adminsConsoleItem.getValue().getId();
                    //Проверяем, что элемент не является пустым и что была нажата правая кнопка мыши
                    if (Variables.adminsConsoleItem != null && event.getButton() == MouseButton.SECONDARY) {
                        //Добавляем реакцию на нажатие кнопки "Добавить"
                        menuAdd.setOnAction(event1 -> {
                            try {
                                Variables.containerConsoleDirection = Direction.MAIN;
                                container.start(stage);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        //Добавляем реакцию на нажатие кнопки "Удалить"
                        menuDelete.setOnAction(event1 -> {
                            //System.out.println(Variables.direction);
                            switch (Variables.adminsConsoleDirection) {
                                case MAIN:
                                    break;
                                default:  Variables.adminsConsoleItem.getParent().getChildren().remove(Variables.adminsConsoleItem);
                                    break;
                            }
                        });
                        //Проверяем, что элемент не является пустым и что была нажата левая кнопка мыши
                    } else if(Variables.adminsConsoleItem != null && event.getButton() == MouseButton.PRIMARY) {

                    }
                }
        );
    }
    //Метод для добавления объекта в дерево системы
    private void addItem(TreeItem treeItem, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        treeItem.setGraphic(imageView);
        Variables.adminsConsoleItem.getChildren().add(treeItem);
    }
}
