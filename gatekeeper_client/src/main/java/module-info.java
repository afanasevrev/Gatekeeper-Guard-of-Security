module com.alrosa.staa.gatekeeper_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires spring.web;
    requires spring.core;
    requires com.rabbitmq.client;
    requires com.google.gson;

    opens com.alrosa.staa.gatekeeper_client to javafx.fxml;
    exports com.alrosa.staa.gatekeeper_client;
    exports com.alrosa.staa.gatekeeper_client.controller;
    opens com.alrosa.staa.gatekeeper_client.controller to javafx.fxml;
    opens com.alrosa.staa.gatekeeper_client.model to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects to com.google.gson;
    exports com.alrosa.staa.gatekeeper_client.controller.messaging;
    opens com.alrosa.staa.gatekeeper_client.controller.messaging to javafx.fxml;
    exports com.alrosa.staa.gatekeeper_client.controller.admins_console;
    opens com.alrosa.staa.gatekeeper_client.controller.admins_console to javafx.fxml;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.card_layouts_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.cards_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.organizations_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.positions_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.users_admins_operators_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.bureau.bureau_objects.organizations_objects.organization_objects to com.google.gson;

    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.computer to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.computer.computer_objects to com.google.gson;

    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.server to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.server.server_objects.perco_objects.percoc01_objects to com.google.gson;
}