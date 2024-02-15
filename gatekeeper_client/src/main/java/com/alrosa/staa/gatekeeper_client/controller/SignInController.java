package com.alrosa.staa.gatekeeper_client.controller;

import com.alrosa.staa.gatekeeper_client.model.Variables;
import com.alrosa.staa.gatekeeper_client.view.AdminsConsole;
import com.alrosa.staa.gatekeeper_client.view.BureausConsole;
import com.alrosa.staa.gatekeeper_client.view.OperatorsConsole;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    private static String accessToken;

    //Создаем экземпляр класса AdminsConsole
    AdminsConsole adminsConsole = new AdminsConsole();
    //Создаем экземпляр класса OperatorsConsole
    OperatorsConsole operatorsConsole = new OperatorsConsole();
    //Создаем экземпляр класса BureausConsole
    BureausConsole bureausConsole = new BureausConsole();
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
        //authentication(loginTextField.getText(), passwordField.getText());
        authenticate(loginTextField.getText(), passwordField.getText());
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

            //Выводим ответ
            String text = response.toString();
            //Выводим логи
            logsTextArea.setText(text);

            //Проверяем аутентификацию
            if (text.equals("AUTHENTICATION:ADMIN")) {
                //Закрываем форму ввода логина и пароля
                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();
                //Запускаем админскую консоль
                adminsConsole.start(new Stage());
            } else if (text.equals("AUTHENTICATION:OPERATOR")) {
                //Закрываем форму ввода логина и пароля
                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();
                //Запускаем консоль оператора
                operatorsConsole.start(new Stage());
            } else if (text.equals("AUTHENTICATION:BUREAU")) {
                //Закрываем форму ввода логина и пароля
                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();
                //Запускаем консоль бюро пропусков
                bureausConsole.start(new Stage());
            } else {
                logsTextArea.setText("Неверный логин или пароль");
            }

        } catch (Exception e) {
            //Выводим логи в окно консоли, если что-то пошло не так
            logsTextArea.setText(e.getMessage());
        }
    }

    public void authenticate(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(login, password);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/authenticate", HttpMethod.GET, request, String.class);
        accessToken = response.getBody();
        System.out.println(accessToken);
    }
}