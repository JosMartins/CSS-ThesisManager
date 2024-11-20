package pt.ul.fc.css.javafxclient.presentation.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pt.ul.fc.css.javafxclient.HelloThesis;
import pt.ul.fc.css.javafxclient.presentation.model.Aluno;

public class MenuController {

    Aluno alunoLogado;

    public void handleLogout() {
        this.alunoLogado = null;
        //TODO Send a logout request to the server

        //wait for response

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logout successful");
        alert.showAndWait();

        //Redirect to login page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(HelloThesis.PREFIX + "login.fxml"));
            Parent root = loader.load();
            Stage stage = HelloThesis.getPrimaryStage();
            stage.setScene(new Scene(root, root.prefWidth(-1), root.prefHeight(-1)));
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setAluno(Aluno aluno) {
        this.alunoLogado = aluno;
    }
}
