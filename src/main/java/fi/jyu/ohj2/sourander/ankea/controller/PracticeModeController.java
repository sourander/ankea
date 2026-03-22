package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.Flashcard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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

    private final BooleanProperty backRevealed = new SimpleBooleanProperty(false);
    private String currentBackText = "";

    private final IntegerProperty currentCardIndex = new SimpleIntegerProperty(-1);
    private final IntegerProperty practiceCardCount = new SimpleIntegerProperty(0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // User has no need to touch these
        frontTextArea.setEditable(false);
        backTextArea.setEditable(false);

        prevButton.disableProperty().bind(
            currentCardIndex.lessThanOrEqualTo(0)
        );

        nextButton.disableProperty().bind(
            currentCardIndex.greaterThanOrEqualTo(practiceCardCount.subtract(1))
        );

        currentCardIndex.addListener((observable, oldValue, newValue) -> {
            updateCardDisplay();
        });

        prevButton.setOnAction(event -> practicePrevCard());
        exitButton.setOnAction(event -> closeWindow());
        nextButton.setOnAction(event -> practiceNextCard());

        backTextArea.setOnMouseClicked(event -> backRevealed.set(true));

        backRevealed.addListener((obs, old, isNowRevealed) -> applyRevealStyle(isNowRevealed));
        applyRevealStyle(false);

        exitButton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
            }
        });
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            practicePrevCard();
            event.consume();
        } else if (event.getCode() == KeyCode.RIGHT) {
            practiceNextCard();
            event.consume();
        } else if (event.getCode() == KeyCode.SPACE) {
            if (!backRevealed.get()) {
                backRevealed.set(true);
            }
            event.consume();
        }
    }

    private void applyRevealStyle(boolean isRevealed) {
        if (isRevealed) {
            backTextArea.setText(currentBackText);
            backTextArea.setStyle("-fx-control-inner-background: #ffffff; -fx-text-fill: #000000;"); 
        } else {
            backTextArea.setText("Click to reveal answer");
            backTextArea.setStyle("-fx-control-inner-background: #e0e0e0; -fx-text-fill: #757575;");
        }
    }

    private void practicePrevCard() {
        if (currentCardIndex.get() > 0) {
            currentCardIndex.set(currentCardIndex.get() - 1);
        }
    }

    private void practiceNextCard() {
        if (deck != null && currentCardIndex.get() < practiceCardCount.get() - 1) {
            currentCardIndex.set(currentCardIndex.get() + 1);
        }
    }

    private void updateCardDisplay() {
        int index = currentCardIndex.get();
        if (deck != null && index >= 0 && index < practiceCardCount.get()) {
            Flashcard current = deck.getPracticeFlashcard(index);
            
            frontTextArea.setText(current.getFront());
            currentBackText = current.getBack();
            
            backRevealed.set(false); 
        }
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        deck.startPracticeSession();
        practiceCardCount.set(deck.getPracticeCardCount());

        if (practiceCardCount.get() > 0) {
            currentCardIndex.set(0); 
        } else {
            currentCardIndex.set(-1);
            frontTextArea.setText("No flashcards in this deck.");
            currentBackText = "";
            backRevealed.set(false);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        deck.incrementPracticeCount();
    }
}