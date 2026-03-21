package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
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

    private Deck deck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        prevButton.setOnAction(event -> {
            System.out.println("Showing previous card...");
        });

        exitButton.setOnAction(event -> {
            System.out.println("Exiting practice mode...");
            closeWindow();
        });

        nextButton.setOnAction(event -> {
            System.out.println("Showing next card...");
        });

    }

    private void closeWindow() {
        ((Stage) exitButton.getScene().getWindow()).close();
    }

    /** Set a Deck for practice */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
}