package fi.jyu.ohj2.sourander.ankea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point for the Ankea JavaFX application.
 *
 * <p>Loads {@code main.fxml}, attaches the resulting scene to the primary
 * stage, and shows the window.
 */
public class App extends Application {

    /** The single {@link Scene} shared across the application lifecycle. */
    private static Scene scene;

    /**
     * Initialises and displays the primary stage.
     *
     * <p>Loads {@code main.fxml} from the same resource package as this class,
     * wraps it in a 640 × 480 scene, and shows the stage.
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws IOException if the FXML resource cannot be loaded
     */
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

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments forwarded to the JavaFX runtime
     */
    public static void main(String[] args) {
        launch();
    }
}
