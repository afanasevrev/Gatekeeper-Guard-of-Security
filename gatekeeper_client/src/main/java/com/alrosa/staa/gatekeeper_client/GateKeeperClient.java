package com.alrosa.staa.gatekeeper_client;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Точка входа в приложение
 */
public class GateKeeperClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GateKeeperClient.class.getResource("sign_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 350);
        stage.setTitle("GateKeeper - Guard of Security");
        stage.getIcons().add(Variables.shieldImage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}