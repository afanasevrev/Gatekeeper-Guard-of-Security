package com.alrosa.staa.gatekeeper_client.controller;

import com.alrosa.staa.gatekeeper_client.cookie.CookieRestTemplateInterceptor;
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
import java.util.Arrays;
import java.util.List;

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
        authenticate(loginTextField.getText(), passwordField.getText());
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

            String text;
            RestTemplate restTemplate = new RestTemplate();
            List<String> cookies = Arrays.asList("SessionID=D64BE8256A2F1E69CCA5A742203FE612");
            restTemplate.getInterceptors().add(new CookieRestTemplateInterceptor(cookies));

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.setBasicAuth(login, password);
            HttpEntity<String> request = new HttpEntity<>(httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
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
}