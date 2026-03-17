package fi.jyu.ohj2.sourander.ankea.model;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link DeckManager}.
 */
class DeckManagerTest {

    /**
     * Verifies a new manager starts empty.
     */
    @Test
    void defaultConstructorCreatesEmptyManager() {
        DeckManager deckManager = new DeckManager();

        assertEquals(0, deckManager.getDeckCount());
        assertTrue(deckManager.getDecks().isEmpty());
    }

    /**
     * Verifies add and remove operations on the deck list.
     */
    @Test
    void addAndRemoveDecksWork() {
        DeckManager deckManager = new DeckManager();
        Deck lang = new Deck("Klingon");
        Deck hhgttg = new Deck("Hitchhiker's Guide to the Galaxy");

        deckManager.addDeck(lang);
        deckManager.addDeck(hhgttg);

        assertEquals(2, deckManager.getDeckCount());
        assertTrue(deckManager.getDecks().contains(lang));
        assertTrue(deckManager.removeDeck(lang));
        assertFalse(deckManager.getDecks().contains(lang));
    }

    /**
     * Verifies replacing and clearing the managed deck list.
     */
    @Test
    void setDecksAndClearDecksUpdateCollection() {
        DeckManager deckManager = new DeckManager();
        Deck python = new Deck("Python programming with real snakes");

        deckManager.setDecks(FXCollections.observableArrayList(python));

        assertEquals(1, deckManager.getDeckCount());
        assertEquals(python, deckManager.getDecks().getFirst());

        deckManager.clearDecks();

        assertTrue(deckManager.getDecks().isEmpty());
    }
}