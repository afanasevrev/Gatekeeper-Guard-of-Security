package com.alrosa.staa.gatekeeper_client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Контроллер предназначен для работы с файлом sign_in.fxml
 */
public class SignInController {
    @FXML
    private TextField loginTextField = new TextField();
    @FXML
    private PasswordField passwordField = new PasswordField();
    @FXML
    private Button signInButton = new Button();
    @FXML
    private TextArea logsTextArea = new TextArea();
    

}