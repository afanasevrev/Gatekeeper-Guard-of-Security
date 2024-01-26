package com.alrosa.staa.gatekeeper_client.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

/**
 * Контроллер предназначен для работы с файлом sign_in.fxml
 */
public class SignInController implements Initializable {
    @FXML
    private TextField loginTextField = new TextField();
    @FXML
    private PasswordField passwordField = new PasswordField();
    @FXML
    private Button signInButton = new Button();
    @FXML
    private TextArea logsTextArea = new TextArea();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     //   URL url = null;
        try {
            url = new URL("http://localhost:8080/");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //        return in.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}