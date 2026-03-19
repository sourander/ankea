package fi.jyu.ohj2.sourander.ankea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCardController implements Initializable {

    @FXML
    private TextArea frontTextArea;

    @FXML
    private TextArea backTextArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveButton.setOnAction(event -> {
            System.out.println("Saving card...");
        });

        cancelButton.setOnAction(event -> {
            System.out.println("Canceling card edit...");
        });

    }

}
