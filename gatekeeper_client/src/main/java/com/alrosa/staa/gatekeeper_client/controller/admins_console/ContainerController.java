package com.alrosa.staa.gatekeeper_client.controller.admins_console;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Класс - контроллер для контейнера
 * В контейнере будем выбирать объекты
 * и добавлять их в дерево системы
 */
public class ContainerController implements Initializable {
    @FXML
    private AnchorPane anchorPane = new AnchorPane();
    @FXML
    private Button buttonCreate = new Button();
    @FXML
    private Button buttonCancel = new Button();
    //Инициализируем дерево в контейнере
    private TreeView container = new TreeView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void isPressedButtonCreate() {}

    @FXML
    private void isPressedButtonCancel() {

    }
}
