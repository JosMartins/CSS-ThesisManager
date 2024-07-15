package pt.ul.fc.css.javafxclient.presentation.control;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.util.Callback;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ul.fc.css.javafxclient.HelloThesis;
import pt.ul.fc.css.javafxclient.presentation.model.Aluno;
import pt.ul.fc.css.javafxclient.presentation.model.CandidaturaTese;
import pt.ul.fc.css.javafxclient.presentation.model.Tema;

public class TemasController {

    private Aluno alunoLogado;

    private CandidaturaTese candTese;

    private List<Tema> temas;

    @FXML
    private ListView<Tema> temasListView;

    @FXML
    public void initialize() {
        this.temas = fetchTemasFromApi();

        temasListView.setItems(FXCollections.observableArrayList(this.temas));


        temasListView.setCellFactory(new Callback<ListView<Tema>, ListCell<Tema>>() {
            @Override
            public ListCell<Tema> call(ListView<Tema> param) {
                return new ListCell<Tema>() {
                    @Override
                    protected void updateItem(Tema item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.getTitulo()); // Assuming Tema has a getName() method
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void handleTemasButtonAction(ActionEvent event) {

        List<Tema> temas = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/temas"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(List.class, Tema.class);
                temas = objectMapper.readValue(response.body(), collectionType);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to retrieve temas data from API");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to retrieve temas data from API");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCandidatarButtonAction(ActionEvent Event) {

        CandidaturaTese cand = new CandidaturaTese();
        List<Tema> candTemas = new ArrayList<>(temas);
        candTemas = candTemas.subList(0, 5);
        cand.setAluno(alunoLogado);
        cand.setListaTemas(candTemas);

        boolean sucess = candidatarApi(cand);
        if (sucess) {
            candTese = cand;
        }

    }

    @FXML
    private void handleOpenSubmissionWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/javafx/submission.fxml"));
            Parent root = fxmlLoader.load();
            SubmissionController subControler = fxmlLoader.getController();
            subControler.setAluno(alunoLogado);


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Submit Documents");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean candidatarApi(CandidaturaTese cand) {

        try {
            String body = new ObjectMapper().writeValueAsString(cand);
            System.out.println(body);
           // HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/candidaturatese"))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("Content-Type", "application/json")
                    .build();

            //HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
/*
            if (response.statusCode() == 201) {
                System.out.println("Candidatura created successfully");
                return true;
            } else {
                System.out.println("Failed to create candidatura: " + response.statusCode());
            }
  */      } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while creating candidatura");
        }

        return false;

    }

    private List<Tema> fetchTemasFromApi() {
        List<Tema> temasList = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/temas"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(List.class, Tema.class);

                temasList = objectMapper.readValue(response.body(), collectionType);
            }

            return temasList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temasList;
    }

    public void setAluno(Aluno aluno) {
        alunoLogado = aluno;
    }

    protected boolean cancelTema() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/candidaturatese/cancelar";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CandidaturaTese> requestEntity = new HttpEntity<>(candTese, headers);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    @FXML
    public void handleLogoutAction(ActionEvent event) {

        if (alunoLogado != null) {


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create("http://localhost:8080/api/aluno/logout")).
                    POST(HttpRequest.BodyPublishers.ofString("{\"username\":\"" + alunoLogado.getUsername() + "\"}")).
                    header("Content-Type", "application/json").
                    build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    alunoLogado = null;
                }

                FXMLLoader loader = new FXMLLoader(TemasController.class.getResource(HelloThesis.PREFIX + "login.fxml"));
                Parent root = loader.load();

                Stage loginStage = new Stage();
                loginStage.setTitle("Login Window");
                loginStage.setScene(new Scene(root, 300, 275));
                loginStage.show();

                ((Node) event.getSource()).getScene().getWindow().hide();


            } catch (IOException | InterruptedException e) {

            }
        }
    }

}


