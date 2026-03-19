package fi.jyu.ohj2.sourander.ankea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import fi.jyu.ohj2.sourander.ankea.model.Deck;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeckController implements Initializable {

    @FXML
    private TextArea frontTextArea;

    @FXML
    private TextArea backTextArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Deck deck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        saveButton.setOnAction(event -> {
            System.out.println("Saving deck..." + deck.getHeader());
        });

        cancelButton.setOnAction(event -> {
            System.out.println("Canceling deck edit..." + deck.getHeader());
        });

    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
}
