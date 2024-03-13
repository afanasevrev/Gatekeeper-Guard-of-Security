package com.alrosa.staa.gatekeeper_client.controller.admins_console;

import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    //Создаем вершину дерева
    private TreeItem<Global> globalTreeItem = new TreeItem(new Main());
    //Создаем рисунок главного объекта в ImageView
    private final ImageView globalView = new ImageView(Variables.mainImage);
    //Создаем дерево в контейнере
    private TreeView global = new TreeView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setRightAnchor(global, 0.0);
        AnchorPane.setTopAnchor(global, 0.0);
        AnchorPane.setLeftAnchor(global, 0.0);
        AnchorPane.setBottomAnchor(global, 0.0);
        anchorPane.getChildren().addAll(global);
        globalView.setFitHeight(25);
        globalView.setFitWidth(25);
        globalTreeItem.setGraphic(globalView);
        addItems(Variables.adminsConsoleDirection);
        //Раскрываем дерево полностью
        globalTreeItem.setExpanded(true);
    }

    @FXML
    private void isPressedButtonCreate() {}

    /**
     * Закрываем окно при нажатии кнопки "Отмена"
     */
    @FXML
    private void isPressedButtonCancel() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
    private void addItems(Direction direction) {
        globalTreeItem.getChildren().clear();
        switch (direction) {
            case MAIN:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.serverTreeItem,
                                Variables.computerTreeItem,
                                Variables.bureauTreeItem
                        );
                break;
            case SERVER:
                globalTreeItem.getChildren().addAll(Variables.percoTreeItem);
                break;
            case PERCO:
                globalTreeItem.getChildren().addAll(Variables.PERCoC01TreeItem);
                break;
            case PERCOC01:
                globalTreeItem.getChildren().addAll(Variables.cardReaderTreeItem);
                break;
            case COMPUTER:
                globalTreeItem.getChildren().addAll(Variables.consoleTreeItem);
                break;
            case BUREAU:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.usersTreeItem,
                                Variables.adminsTreeItem,
                                Variables.operatorsTreeItem,
                                Variables.globalAccessLevelsTreeItem,
                                Variables.cardsTreeItem,
                                Variables.cardLayoutsTreeItem,
                                Variables.positionsTreeItem,
                                Variables.organizationsTreeItem
                        );
                break;
            case USERS:
            case ADMINS:
            case OPERATORS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.manTreeItem,
                                Variables.womanTreeItem
                        );
                break;
            case GLOBAL_ACCESS_LEVELS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.globalAccessLevelTreeItem
                        );
                break;
            case CARDS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.cardTreeItem
                        );
                break;
            case CARD_LAYOUTS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.cardLayoutTreeItem
                        );
                break;
            case POSITIONS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.positionTreeItem
                        );
                break;
            case ORGANIZATIONS:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.organizationTreeItem
                        );
                break;
            case ORGANIZATION:
                globalTreeItem.getChildren().addAll
                        (
                                Variables.officeTreeItem
                        );
                break;
            default:   System.out.println("Selected item: default");
                break;
        }
    }
}
