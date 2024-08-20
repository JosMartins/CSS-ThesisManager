package pt.ul.fc.css.javafxclient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ul.fc.css.javafxclient.presentation.control.LoginController;
import pt.ul.fc.css.javafxclient.presentation.control.TemasController;

public class HelloThesis extends Application {

    public static final String PREFIX = "/javafx/";

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(PREFIX + "login.fxml"));
        Parent root = loader.load();


        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
