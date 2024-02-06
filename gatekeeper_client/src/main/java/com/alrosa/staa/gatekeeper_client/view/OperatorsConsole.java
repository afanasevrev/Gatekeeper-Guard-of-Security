package com.alrosa.staa.gatekeeper_client.view;

import com.alrosa.staa.gatekeeper_client.GateKeeperClient;
import com.alrosa.staa.gatekeeper_client.model.Variables;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OperatorsConsole {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GateKeeperClient.class.getResource("console/operator/operators_console.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 350);
        stage.setTitle("GateKeeper - Guard of Security");
        stage.getIcons().add(Variables.shieldImage);
        stage.setScene(scene);
        stage.show();
    }
}
