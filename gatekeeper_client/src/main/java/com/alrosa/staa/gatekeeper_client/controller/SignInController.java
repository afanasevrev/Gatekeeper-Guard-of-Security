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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
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
        try {
            authentication();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void authentication() throws IOException, InterruptedException {
        try {
            // URL, на который отправляем запрос
            String url = "http://localhost:8080/";

            // Создаем объект URL
            URL obj = new URL(url);

            // Открываем соединение
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Устанавливаем метод запроса
            con.setRequestMethod("GET");

            // Устанавливаем заголовок для basic авторизации
            String username = "root";
            String password = "gatekeeper";
            String credentials = username + ":" + password;
            byte[] authEncBytes = Base64.getEncoder().encode(credentials.getBytes());
            String authStringEnc = new String(authEncBytes);
            con.setRequestProperty("Authorization", "Basic " + authStringEnc);

            // Получаем ответ
            int responseCode = con.getResponseCode();
            System.out.println("Отправляем GET запрос на " + url);
            System.out.println("Ответ: " + responseCode);

            // Считываем ответ
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Выводим ответ
            System.out.println(response.toString());
            logsTextArea.setText(response.toString());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}