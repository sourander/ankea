package fi.jyu.ohj2.sourander.ankea.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * Represents a flashcard deck with metadata, cards, and practice statistics.
 */
public class Deck {

    /** Default header used when a deck is created without a user-provided name. */
    public static final String DEFAULT_HEADER = "This one has no name";

    /** Short name for the deck. Editable by the user, but never null or blank. */
    private final StringProperty header = new SimpleStringProperty(DEFAULT_HEADER);

    /** (Optional) longer description for the deck. */
    private final StringProperty description = new SimpleStringProperty("");

    /** Cards contained in the deck. */
    private final ListProperty<Flashcard> flashcards =
            new SimpleListProperty<>(FXCollections.observableArrayList());

    /** Tracks how many full practice rounds have been completed for the deck. */
    private final IntegerProperty practiceCount = new SimpleIntegerProperty(0);

    /**
     * Creates an empty deck. Not used, but JavaFX requires this 
     */
    public Deck() {
        setHeader(DEFAULT_HEADER);
    }

    /**
     * Creates a deck with a header.
     *
     * @param header the deck header
     */
    public Deck(String header) {
        setHeader(header);
    }

    /**
     * Creates a deck with a header and description.
     *
     * @param header      the deck header
     * @param description the deck description
     */
    public Deck(String header, String description) {
        setHeader(header);
        setDescription(description);
    }

    /**
     * Returns the deck header.
     *
     * @return the deck header
     */
    public String getHeader() {
        return header.get();
    }

    /**
     * Updates the deck header.
     *
     * @param header the new deck header
     */
    public void setHeader(String header) {
        if (header == null) {
            throw new IllegalArgumentException("header must not be null");
        }

        if (header.isBlank()) {
            throw new IllegalArgumentException("header must not be blank");
        }

        this.header.set(header);
    }

    /**
     * Returns the deck header property.
     *
     * @return the deck header property
     */
    public StringProperty headerProperty() {
        return header;
    }

    /**
     * Returns the deck description.
     *
     * @return the deck description
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Updates the deck description.
     *
     * @param description the new deck description
     */
    public void setDescription(String description) {
        this.description.set(description == null ? "" : description);
    }

    /**
     * Returns the deck description property.
     *
     * @return the deck description property
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Returns the practice count.
     *
     * @return the deck practice count
     */
    public int getPracticeCount() {
        return practiceCount.get();
    }

    /**
     * Updates the practice count.
     *
     * @param practiceCount the new practice count
     */
    public void setPracticeCount(int practiceCount) {
        this.practiceCount.set(practiceCount);
    }

    /**
     * Returns the practice count property.
     *
     * @return the practice count property
     */
    public IntegerProperty practiceCountProperty() {
        return practiceCount;
    }

    /**
     * Increments the practice count by one.
     */
    public void incrementPracticeCount() {
        setPracticeCount(getPracticeCount() + 1);
    }

    /**
     * Returns the observable list of flashcards.
     *
     * @return the flashcards in this deck
     */
    public ObservableList<Flashcard> getFlashcards() {
        return flashcards.get();
    }

    /**
     * Returns the flashcards list property.
     *
     * @return the flashcards list property
     */
    public ListProperty<Flashcard> flashcardsProperty() {
        return flashcards;
    }

    /**
     * Replaces the flashcards list content.
     *
     * @param flashcards the new flashcard list content
     */
    public void setFlashcards(ObservableList<Flashcard> flashcards) {
        this.flashcards.set(FXCollections.observableArrayList(
                Objects.requireNonNullElseGet(flashcards, FXCollections::observableArrayList)
        ));
    }

    /**
     * Adds a flashcard to the deck.
     *
     * @param flashcard the flashcard to add
     */
    public void addFlashcard(Flashcard flashcard) {
        getFlashcards().add(Objects.requireNonNull(flashcard, "flashcard must not be null"));
    }

    /**
     * Removes a flashcard from the deck.
     *
     * @param flashcard the flashcard to remove
     * @return {@code true} if the flashcard was removed, otherwise {@code false}
     */
    public boolean removeFlashcard(Flashcard flashcard) {
        return getFlashcards().remove(flashcard);
    }

    /**
     * Removes all flashcards from the deck.
     */
    public void clearFlashcards() {
        getFlashcards().clear();
    }

    /**
     * Returns the number of flashcards in the deck.
     *
     * @return the number of flashcards in the deck
     */
    public int getFlashcardCount() {
        return getFlashcards().size();
    }

    private void shuffle() {
        FXCollections.shuffle(getFlashcards());
    }

    //** Returns a copy of the Deck, but shuffled */
    public Deck shuffledCopy() {
            Deck copy = new Deck(getHeader(), getDescription());
            copy.setPracticeCount(getPracticeCount());
            copy.setFlashcards(FXCollections.observableArrayList(getFlashcards()));
            copy.shuffle();
            return copy;
    }

    @Override
    public String toString() {
        return getHeader();
    }
}