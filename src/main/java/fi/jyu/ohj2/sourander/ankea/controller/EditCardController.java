package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.Flashcard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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

    private Deck deck;
    private Flashcard card;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(event -> {
            card.setFront(frontTextArea.getText());
            card.setBack(backTextArea.getText());
            if (!deck.flashcardsProperty().contains(card)) {
                deck.addFlashcard(card);
            }
            closeWindow();
        });

        cancelButton.setOnAction(event -> closeWindow());
    }

    public void setCard(Deck deck, Flashcard card) {
        this.deck = deck;
        this.card = card;
        frontTextArea.setText(card.getFront());
        backTextArea.setText(card.getBack());
    }

    private void closeWindow() {
        ((Stage) saveButton.getScene().getWindow()).close();
    }
}
