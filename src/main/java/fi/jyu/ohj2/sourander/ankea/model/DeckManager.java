package fi.jyu.ohj2.sourander.ankea.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * Root domain model that owns all decks in the application.
 */
public class DeckManager {

    /** The observable list of decks managed by the application. */
    private final ListProperty<Deck> decks =
            new SimpleListProperty<>(FXCollections.observableArrayList());

    /**
     * Creates an empty deck manager.
     */
    public DeckManager() {
        // Default constructor for JavaFX-friendly model creation.
    }

    /**
     * Returns the observable list of decks.
     *
     * @return the decks managed by this object
     */
    public ObservableList<Deck> getDecks() {
        return decks.get();
    }

    /**
     * Returns the decks list property.
     *
     * @return the decks list property
     */
    public ListProperty<Deck> decksProperty() {
        return decks;
    }

    /**
     * Replaces the decks list content.
     *
     * @param decks the new deck list content
     */
    public void setDecks(ObservableList<Deck> decks) {
        this.decks.set(FXCollections.observableArrayList(
                Objects.requireNonNullElseGet(decks, FXCollections::observableArrayList)
        ));
    }

    /**
     * Adds a deck to the manager.
     *
     * @param deck the deck to add
     */
    public void addDeck(Deck deck) {
        getDecks().add(Objects.requireNonNull(deck, "deck must not be null"));
    }

    /**
     * Removes a deck from the manager.
     *
     * @param deck the deck to remove
     * @return {@code true} if the deck was removed, otherwise {@code false}
     */
    public boolean removeDeck(Deck deck) {
        return getDecks().remove(deck);
    }

    /**
     * Removes all decks.
     */
    public void clearDecks() {
        getDecks().clear();
    }

    /**
     * Returns the number of managed decks.
     *
     * @return the number of decks
     */
    public int getDeckCount() {
        return getDecks().size();
    }

    @Override
    public String toString() {
        return "DeckManager[decks=" + getDeckCount() + "]";
    }
}