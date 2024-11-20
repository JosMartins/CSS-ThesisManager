package pt.ul.fc.css.javafxclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloThesis extends Application {

    private static Stage primaryStage;

    public static final String PREFIX = "/javafx/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set the primary stage
        HelloThesis.primaryStage = primaryStage;

        //Load the login screen
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(PREFIX + "login.fxml"));
        Parent root = loader.load();


        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, root.prefWidth(-1), root.prefHeight(-1)));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Get the primary stage
     *
     * @return the primary stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
