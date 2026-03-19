package fi.jyu.ohj2.sourander.ankea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PracticeModeController implements Initializable {

    @FXML
    private TextArea frontTextArea;

    @FXML
    private TextArea backTextArea;

    @FXML
    private Button prevButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        prevButton.setOnAction(event -> {
            System.out.println("Showing previous card...");
        });

        exitButton.setOnAction(event -> {
            System.out.println("Exiting practice mode...");
        });

        nextButton.setOnAction(event -> {
            System.out.println("Showing next card...");
        });

    }
    
}