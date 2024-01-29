module com.alrosa.staa.gatekeeper_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens com.alrosa.staa.gatekeeper_client to javafx.fxml;
    exports com.alrosa.staa.gatekeeper_client;
    exports com.alrosa.staa.gatekeeper_client.controller;
    opens com.alrosa.staa.gatekeeper_client.controller to javafx.fxml;
}