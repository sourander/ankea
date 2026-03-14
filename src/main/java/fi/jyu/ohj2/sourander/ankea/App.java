package fi.jyu.ohj2.sourander.ankea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Because the FXML is in the matching resources folder, 
        // getClass().getResource() easily finds it relative to this class.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Ankea v0.42.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
