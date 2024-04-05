package com.alrosa.staa.gatekeeper_client.controller.admins_console;

import com.alrosa.staa.gatekeeper_client.controller.messaging.Transmitter;
import com.alrosa.staa.gatekeeper_client.model.Direction;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.General;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Global;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.Main;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.Bureau;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.*;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.card_layouts_objects.CardLayout;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.cards_objects.Card;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.global_access_levels_objects.GlobalAccessLevel;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.organizations_objects.Organization;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.organizations_objects.organization_objects.Office;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.positions_objects.Position;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.users_admins_operators_objects.Man;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.users_admins_operators_objects.Woman;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.computer.Computer;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.computer.computer_objects.Console;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.Server;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.Perco;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects.PERCoC01;
import com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects.percoc01_objects.CardReader;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
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
    private TreeView global = new TreeView(globalTreeItem);
    //Создаем текст передаваемого сообщения
    String text = new String();
    //Создаем JSON для отправки на сервер
    Gson gson = new Gson();
    //Создаем экземпляр класса Transmitter
    Transmitter transmitter = Transmitter.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Выравниваем наше дерево по всем краям окна
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
        /**
         * При нажатии на объект, метод записывает в статическую переменную enum выбранного объекта
         */
        global.setOnMouseClicked(event -> {
            Variables.containerConsoleItem = (TreeItem<Global>) global.getSelectionModel().getSelectedItem();
            Variables.containerConsoleDirection = Variables.containerConsoleItem.getValue().getDirection();
        });
    }

    /**
     * При нажатии кнопки "Добавить" добавляем в главное дерево объекты (вершины)
     */
    @FXML
    private void isPressedButtonCreate() throws Exception {
        General general = new General(Variables.containerConsoleDirection, Variables.parentId);
        TreeItem<Global> item;
        ImageView imageView;
        switch (Variables.containerConsoleDirection) {
            case MAIN:
                break;
            case SERVER:
                text = gson.toJson(general);
                break;
            case COMPUTER:
                Computer computer = new Computer();
                text = gson.toJson(computer);
                item = new TreeItem<>(computer);
                imageView = new ImageView(Variables.imageComputer);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case BUREAU:
                //Bureau bureau = new Bureau();
                text = gson.toJson(general);
                //item = new TreeItem<>(bureau);
                //imageView = new ImageView(Variables.imageBureau);
                //imageView.setFitHeight(25);
                //imageView.setFitWidth(25);
                //item.setGraphic(imageView);
                //Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case PERCO:
                Perco perco = new Perco();
                text = gson.toJson(perco);
                item = new TreeItem<>(perco);
                imageView = new ImageView(Variables.imagePerco);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case PERCOC01:
                PERCoC01 perCoC01 = new PERCoC01();
                text = gson.toJson(perCoC01);
                item = new TreeItem<>(perCoC01);
                imageView = new ImageView(Variables.imagePERCoC01);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CARDREADER:
                CardReader cardReader = new CardReader();
                text = gson.toJson(cardReader);
                item = new TreeItem<>(cardReader);
                imageView = new ImageView(Variables.imageCardReader);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CONSOLE:
                Console console = new Console();
                text = gson.toJson(console);
                item = new TreeItem<>(console);
                imageView = new ImageView(Variables.imageConsole);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case USERS:
                Users users = new Users();
                text = gson.toJson(users);
                item = new TreeItem<>(users);
                imageView = new ImageView(Variables.imageUsers);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case ADMINS:
                Admins admins = new Admins();
                text = gson.toJson(admins);
                item = new TreeItem<>(admins);
                imageView = new ImageView(Variables.imageAdmins);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case OPERATORS:
                Operators operators = new Operators();
                text = gson.toJson(operators);
                item = new TreeItem<>(operators);
                imageView = new ImageView(Variables.imageOperators);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case GLOBAL_ACCESS_LEVELS:
                GlobalAccessLevels globalAccessLevels = new GlobalAccessLevels();
                text = gson.toJson(globalAccessLevels);
                item = new TreeItem<>(globalAccessLevels);
                imageView = new ImageView(Variables.imageGlobalAccessLevels);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CARDS:
                Cards cards = new Cards();
                text = gson.toJson(cards);
                item = new TreeItem<>(cards);
                imageView = new ImageView(Variables.imageCards);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CARD_LAYOUTS:
                CardLayouts cardLayouts = new CardLayouts();
                text = gson.toJson(cardLayouts);
                item = new TreeItem<>(cardLayouts);
                imageView = new ImageView(Variables.imageCardLayouts);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case POSITIONS:
                Positions positions = new Positions();
                text = gson.toJson(positions);
                item = new TreeItem<>(positions);
                imageView = new ImageView(Variables.imagePositions);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case ORGANIZATIONS:
                Organizations organizations = new Organizations();
                text = gson.toJson(organizations);
                item = new TreeItem<>(organizations);
                imageView = new ImageView(Variables.imageOrganizations);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case MAN:
                Man man = new Man();
                text = gson.toJson(man);
                item = new TreeItem<>(man);
                imageView = new ImageView(Variables.imageMan);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case WOMAN:
                Woman woman = new Woman();
                text = gson.toJson(woman);
                item = new TreeItem<>(woman);
                imageView = new ImageView(Variables.imageWoman);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case GLOBAL_ACCESS_LEVEL:
                GlobalAccessLevel globalAccessLevel = new GlobalAccessLevel();
                text = gson.toJson(globalAccessLevel);
                item = new TreeItem<>(globalAccessLevel);
                imageView = new ImageView(Variables.imageGlobalAccessLevel);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CARD:
                Card card = new Card();
                text = gson.toJson(card);
                item = new TreeItem<>(card);
                imageView = new ImageView(Variables.imageCard);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case CARD_LAYOUT:
                CardLayout cardLayout = new CardLayout();
                text = gson.toJson(cardLayout);
                item = new TreeItem<>(cardLayout);
                imageView = new ImageView(Variables.imageCardLayout);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case POSITION:
                Position position = new Position();
                text = gson.toJson(position);
                item = new TreeItem<>(position);
                imageView = new ImageView(Variables.imagePosition);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case ORGANIZATION:
                Organization organization = new Organization();
                text = gson.toJson(organization);
                item = new TreeItem<>(organization);
                imageView = new ImageView(Variables.imageOrganization);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            case OFFICE:
                Office office = new Office();
                text = gson.toJson(office);
                item = new TreeItem<>(new Office());
                imageView = new ImageView(Variables.imageOffice);
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                item.setGraphic(imageView);
                Variables.adminsConsoleItem.getChildren().add(item);
                break;
            default:
                System.out.println(Variables.containerConsoleItem.getValue().getDirection());
                break;
        }
        transmitter.sendMessage(text);
        //Сортируем элементы дерева после необходимых итераций
        Variables.adminsConsoleItem.getChildren().sort(Comparator.comparing(t->t.getValue().toString()));
    }

    /**
     * Закрываем окно при нажатии кнопки "Отмена"
     */
    @FXML
    private void isPressedButtonCancel() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Метод добавляет в контейнер дерево объектов
     * @param direction
     */
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
            default:
                break;
        }
    }
}
