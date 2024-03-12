package com.alrosa.staa.gatekeeper_client.controller.admins_console;

import com.alrosa.staa.gatekeeper_client.controller.sessions.Receiver;
import com.alrosa.staa.gatekeeper_client.controller.sessions.Transceiver;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер для работы с файлом admins_console.fxml
 */
public class AdminsConsoleController implements Initializable {
    //Создаем экземпляр класса Receiver
    Receiver receiver = new Receiver();
    //Создаем экземпляр класса Transceiver
    Transceiver transceiver = new Transceiver();
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

        

        try {
            receiver.start();
            transceiver.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
