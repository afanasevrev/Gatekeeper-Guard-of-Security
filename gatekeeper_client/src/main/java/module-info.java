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
    exports com.alrosa.staa.gatekeeper_client.controller.sessions;
    opens com.alrosa.staa.gatekeeper_client.controller.sessions to javafx.fxml;
    exports com.alrosa.staa.gatekeeper_client.controller.admins_console;
    opens com.alrosa.staa.gatekeeper_client.controller.admins_console to javafx.fxml;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.server to com.google.gson;
    opens com.alrosa.staa.gatekeeper_client.model.tree_objects.computer to com.google.gson;
}