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

public class MainController implements Initializable {

    @FXML
    private Button testButton;

    @FXML
    private TextArea testTextArea;

    @FXML
    private Label testLabel;

    private AnkeaModel model;

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