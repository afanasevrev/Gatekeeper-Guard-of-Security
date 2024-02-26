package com.alrosa.staa.gatekeeper_client.controller;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Контроллер предназначен для работы с файлом admins_console.fxml
 */
public class AdminsConsoleController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //В контекстное меню добавляем кнопки
        contextMenu.getItems().addAll(menuAdd, menuDelete);
        //В наше дерево добавляем контекстное меню
        //treeView.setContextMenu(contextMenu);

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
        getInfo();
    }

    public void getInfo() {

    }
}
