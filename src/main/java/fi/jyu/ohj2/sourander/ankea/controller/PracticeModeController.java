package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.Flashcard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    private Deck shuffledDeck; // This will hold the shuffled cards for practice
    private int n;

    private final IntegerProperty currentCardIndex = new SimpleIntegerProperty(-1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prevButton.disableProperty().bind(
            currentCardIndex.lessThanOrEqualTo(0)
        );

        currentCardIndex.addListener((observable, oldValue, newValue) -> {
            updateCardDisplay();
        });

        prevButton.setOnAction(event -> practicePrevCard());
        exitButton.setOnAction(event -> closeWindow());
        nextButton.setOnAction(event -> practiceNextCard());
    }

    private void practicePrevCard() {
        if (currentCardIndex.get() > 0) {
            currentCardIndex.set(currentCardIndex.get() - 1);
        }
    }

    private void practiceNextCard() {
        if (shuffledDeck != null && currentCardIndex.get() < shuffledDeck.getFlashcards().size() - 1) {
            currentCardIndex.set(currentCardIndex.get() + 1);
        }
    }

    private void updateCardDisplay() {
        int index = currentCardIndex.get();
        if (shuffledDeck != null && index >= 0 && index < shuffledDeck.getFlashcards().size()) {
            Flashcard current = shuffledDeck.getFlashcards().get(index);
            frontTextArea.setText(current.getFront());
            backTextArea.setText(current.getBack());
        }
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        this.shuffledDeck = deck.shuffledCopy();

        if (!shuffledDeck.getFlashcards().isEmpty()) {
            // 3. Update the binding now that we know the deck size
            nextButton.disableProperty().bind(
                currentCardIndex.greaterThanOrEqualTo(shuffledDeck.getFlashcards().size() - 1)
            );
            currentCardIndex.set(0); 
            updateCardDisplay();
        } else {
            frontTextArea.setText("No flashcards in this deck.");
            prevButton.setDisable(true);
            nextButton.setDisable(true);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        // Update the original deck's practice count
        deck.setPracticeCount(deck.getPracticeCount() + 1);
    }
}