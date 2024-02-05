package com.alrosa.staa.gatekeeper_client.controller;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

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

    /**
     * При нажатии кнопки проходим аутентификацию на сервере
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    private void clickSignInButton() throws IOException, InterruptedException {
        authentication(loginTextField.getText(), passwordField.getText());
    }

    /**
     * Метод для аутентификации на сервере
     * @param login логин
     * @param password пароль
     * @throws IOException
     * @throws InterruptedException
     */
    public void authentication(String login, String password) throws IOException, InterruptedException {
        try {
            String server_ip = Variables.properties.getProperty("server_ip");

            String server_port = Variables.properties.getProperty("server_port");

            // URL, на который отправляем запрос
            String url = "http://" + server_ip + ":" + server_port + "/getAuth";

            // Создаем объект URL
            URL obj = new URL(url);

            // Открываем соединение
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Устанавливаем метод запроса
            con.setRequestMethod("GET");

            // Устанавливаем заголовок для basic авторизации
            String credentials = login + ":" + password;
            byte[] authEncBytes = Base64.getEncoder().encode(credentials.getBytes());
            String authStringEnc = new String(authEncBytes);
            con.setRequestProperty("Authorization", "Basic " + authStringEnc);

            // Получаем ответ
            int responseCode = con.getResponseCode();

            // Считываем ответ
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Выводим ответ
            logsTextArea.setText(response.toString());
        } catch (Exception e) {
            logsTextArea.setText(e.getMessage());
        }
    }
}