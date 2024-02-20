package com.alrosa.staa.gatekeeper_client.controller;

import com.alrosa.staa.gatekeeper_client.cookie.CookieRestTemplate;
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
import java.io.IOException;

/**
 * Контроллер предназначен для работы с файлом sign_in.fxml
 */
public class SignInController {

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
        //authenticate(loginTextField.getText(), passwordField.getText());
        authenticate1(loginTextField.getText(), passwordField.getText());
    }
    /**
     * Метод для аутентификации на сервере
     * @param login логин
     * @param password пароль
     */
    public void authenticate(String login, String password) {
        try {
            //Считываем из конфигурационного файла IP сервера
            String server_ip = Variables.properties.getProperty("server_ip");
            //Считываем из конфигурационного файла порт сервера
            String server_port = Variables.properties.getProperty("server_port");
            // URL, на который отправляем запрос
            String url = "http://" + server_ip + ":" + server_port + "/authenticate";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBasicAuth(login, password);
            httpHeaders.add("Cookie", "SessionID=s42");
            HttpEntity<String> request = new HttpEntity<>(httpHeaders);

            //request.getHeaders().add("Cookie", "JSESSIONID");

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            String text;
            text = response.getBody();
            logsTextArea.setText(text);
            Stage stage = (Stage) signInButton.getScene().getWindow();
            //Проверяем аутентификацию
            if (text.equals("AUTHENTICATION:ADMIN")) {
                //Закрываем форму ввода логина и пароля
                stage.close();
                //Запускаем админскую консоль
                adminsConsole.start(new Stage());
            } else if (text.equals("AUTHENTICATION:OPERATOR")) {
                //Закрываем форму ввода логина и пароля
                stage.close();
                //Запускаем консоль оператора
                operatorsConsole.start(new Stage());
            } else if (text.equals("AUTHENTICATION:BUREAU")) {
                //Закрываем форму ввода логина и пароля
                stage.close();
                //Запускаем консоль бюро пропусков
                bureausConsole.start(new Stage());
            } else {
                logsTextArea.setText("Неверный логин или пароль");
            }
        } catch(Exception e) {
            //Выводим логи в окно консоли, если что-то пошло не так
            logsTextArea.setText(e.getMessage());
        }
    }

    public void authenticate1(String login, String password) {
        String url = "http://localhost:8080/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookies", "JSESSIONID=BF29A544854C8CFF46E3BE8502D956AD");
        headers.setBasicAuth(login, password);

        // Создаем тело запроса
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Отправляем GET запрос на URL для аутентификации
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        System.out.println(response.getStatusCode().is2xxSuccessful());
    }
}