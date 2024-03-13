package com.alrosa.staa.gatekeeper_client.view;

import com.alrosa.staa.gatekeeper_client.GateKeeperClient;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Container {
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GateKeeperClient.class.getResource("console/admin/container.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),400, 400);
        scene.getStylesheets().add(GateKeeperClient.class.getResource("css/styles.css").toExternalForm());
        stage.setTitle("Добавьте объект");
        stage.setResizable(false);
        stage.getIcons().add(Variables.shieldImage);
        stage.setScene(scene);
        stage.show();
    }
}
