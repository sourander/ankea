// src/main/java/fi/jyu/ohj2/sourander/ankea/controller/MainController.java
package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.AnkeaModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX controller for the main application view ({@code main.fxml}).
 *
 * <p>Wires the UI controls declared in FXML to the {@link AnkeaModel}
 * and sets up event handlers after the scene graph has been built.
 */
public class MainController implements Initializable {

    /** The button the user clicks to trigger a model update. */
    @FXML
    private Button testButton;

    /** Text area reserved for extended output (currently unused). */
    @FXML
    private TextArea testTextArea;

    /** Label that displays the latest response from the model. */
    @FXML
    private Label testLabel;

    /** The model instance that backs this controller. */
    private AnkeaModel model;

    /**
     * Called by the JavaFX runtime after all {@code @FXML} fields have been
     * injected.
     *
     * <p>Creates a new {@link AnkeaModel}, sets the initial label text, and
     * registers a click handler on {@link #testButton} that updates
     * {@link #testLabel} with the model's response.
     *
     * @param location  the URL of the FXML document (may be {@code null})
     * @param resources the resource bundle for the root object (may be {@code null})
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new AnkeaModel();
        
        // This should now fire immediately upon load
        testLabel.setText("Hello from the controller!");

        // Handle the button click purely in Java code
        testButton.setOnAction(event -> {
            testLabel.setText(model.generateResponse());
        });
    }
}