module com.alrosa.staa.gatekeeper_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alrosa.staa.gatekeeper_client to javafx.fxml;
    exports com.alrosa.staa.gatekeeper_client;
}