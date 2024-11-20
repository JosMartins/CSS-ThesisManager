package pt.ul.fc.css.javafxclient.presentation.control;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ul.fc.css.javafxclient.HelloThesis;
import pt.ul.fc.css.javafxclient.presentation.model.Aluno;

public class LoginController {
	
	private Aluno alunoLogado;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction (ActionEvent event) {
        String userName = userNameField.getText();
        String password = passwordField.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING );
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields!");
            alert.showAndWait();
            return;
        }
        
        if (checkLogin(userName, password)) {
            try {
            	Alert alert = new Alert (Alert.AlertType.INFORMATION);
            	alert.setTitle("Login Successful");

                FXMLLoader loader = new FXMLLoader(getClass().getResource(HelloThesis.PREFIX + "menu.fxml"));
                Parent root = loader.load();
                MenuController menuController = loader.getController();
                menuController.setAluno(alunoLogado);
                alert.setContentText("Login successful");
                alert.showAndWait();

                Stage menuStage = HelloThesis.getPrimaryStage();
                menuStage.setTitle("Menu");
                menuStage.setScene(new Scene(root, root.prefWidth(-1), root.prefHeight(-1)));
                menuStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
        }
    }


    private boolean checkLogin(String userName, String password) {

        if (userName.equals("admin") && password.equals("admin")) {
            return true;
        }

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/aluno/login"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\"username\":\"" + userName + "\",\"password\":\"" + password + "\"}"))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> responseMap = mapper.readValue(response.body(), new TypeReference<>() {});
                Aluno aluno = mapper.convertValue(responseMap.get("aluno"), Aluno.class);
                String token = (String) responseMap.get("token");
                aluno.setToken(token);
                alunoLogado = aluno;
                return true;
            } else {
                return false;
            }

        } catch (InterruptedException | IOException e) {
            return false;
        }
    }

}


