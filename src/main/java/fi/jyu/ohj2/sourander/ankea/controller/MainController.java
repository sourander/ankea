// src/main/java/fi/jyu/ohj2/sourander/ankea/controller/MainController.java
package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.DeckManager;
import fi.jyu.ohj2.sourander.ankea.model.Flashcard;
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
 * Sets up event handlers. Very observe. Such wow.
 */
public class MainController implements Initializable {

    /** Happy little test button. Increases the flashcard view count. */
    @FXML
    private Button testButton;

    /** Happy little text area. Shows the flashcard back side (Spanish, hola!). */
    @FXML
    private TextArea testTextArea;

    /** Print the view and stats into this box. Will be replaced when correct UI is done. */
    @FXML
    private Label testLabel;

    /** The DeckManager itself. TODO: Implement loading and saving decks. */
    private DeckManager model;

    /**
     * Called by the JavaFX runtime after all {@code @FXML} fields have been
     * injected.
     *
     * Creates a new {@link DeckManager}. Does some funky demo stuff.
     *
     * Will be extended to load real decks and set up the actual UI when... it is time.
     * 
     * @param location  the URL of the FXML document 
     * @param resources the resource bundle for the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new DeckManager();

        Deck demoDeck = new Deck("Spanish Basics", "Hello world stuff");
        Flashcard demoFlashcard = new Flashcard("hola", "hello");
        demoDeck.addFlashcard(demoFlashcard);
        model.addDeck(demoDeck);

        testLabel.setText(demoDeck.getHeader() + ": " + demoFlashcard.getFront());
        testTextArea.setText(demoFlashcard.getBack());
        testButton.setText("Click me!");

        testButton.setOnAction(event -> {
            demoFlashcard.incrementViewCount();
            testLabel.setText(
                    demoDeck.getHeader()
                            + " | "
                            + demoFlashcard.getFront()
                            + " | views: "
                            + demoFlashcard.getViewCount()
            );
        });
    }
}