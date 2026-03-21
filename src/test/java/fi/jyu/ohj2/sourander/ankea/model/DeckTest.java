package fi.jyu.ohj2.sourander.ankea.model;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Deck}.
 */
class DeckTest {

    /**
     * Verifies default property values for a new deck.
     */
    @Test
    void defaultConstructorCreatesEmptyDeck() {
        Deck deck = new Deck();

        assertEquals(Deck.DEFAULT_HEADER, deck.getHeader());
        assertEquals("", deck.getDescription());
        assertEquals(0, deck.getPracticeCount());
        assertEquals(0, deck.getFlashcardCount());
    }

    /**
     * Verifies metadata setters for valid values.
     */
    @Test
    void settersUpdateDeckMetadata() {
        Deck deck = new Deck("French", "Basics");

        assertEquals("French", deck.getHeader());
        assertEquals("Basics", deck.getDescription());

        deck.setHeader("Advanced French");
        deck.setDescription(null);
        deck.setPracticeCount(4);

        assertEquals("Advanced French", deck.getHeader());
        assertEquals("", deck.getDescription());
        assertEquals(4, deck.getPracticeCount());
    }

    /**
     * Verifies the deck header rejects null and blank values.
     */
    @Test
    void headerMustNotBeNullOrBlank() {
        Deck deck = new Deck("French");

        assertThrows(IllegalArgumentException.class, () -> deck.setHeader(null));
        assertThrows(IllegalArgumentException.class, () -> deck.setHeader(""));
        assertThrows(IllegalArgumentException.class, () -> deck.setHeader("   "));
        assertThrows(IllegalArgumentException.class, () -> new Deck(null));
        assertThrows(IllegalArgumentException.class, () -> new Deck("   "));
    }

    /**
     * Verifies card collection mutation helpers.
     */
    @Test
    void addRemoveAndReplaceFlashcardsWork() {
        Deck deck = new Deck("Biology");
        Flashcard cell = new Flashcard("Cell", "Basic unit of life");
        Flashcard atom = new Flashcard("Atom", "Basic unit of matter");

        deck.addFlashcard(cell);
        deck.addFlashcard(atom);

        assertEquals(2, deck.getFlashcardCount());
        assertTrue(deck.getFlashcards().contains(cell));
        assertTrue(deck.removeFlashcard(cell));
        assertFalse(deck.getFlashcards().contains(cell));

        deck.setFlashcards(FXCollections.observableArrayList(cell));

        assertEquals(1, deck.getFlashcardCount());
        assertEquals(cell, deck.getFlashcards().getFirst());
    }

    /**
     * Verifies the practice counter helper.
     */
    @Test
    void incrementPracticeCountIncreasesCounter() {
        Deck deck = new Deck();

        deck.incrementPracticeCount();
        deck.incrementPracticeCount();

        assertEquals(2, deck.getPracticeCount());
    }

    /**
     * Verifies shuffled copy keeps metadata and cards while creating a new instance.
     */
    @Test
    void shuffledCopyCreatesIndependentDeckWithSameCards() {
        Deck deck = new Deck("French", "Basics");
        deck.setPracticeCount(3);

        Flashcard one = new Flashcard("bonjour", "hello");
        Flashcard two = new Flashcard("chat", "cat");
        Flashcard three = new Flashcard("chien", "dog");

        deck.addFlashcard(one);
        deck.addFlashcard(two);
        deck.addFlashcard(three);

        Deck copy = deck.shuffledCopy();

        assertNotSame(deck, copy);
        assertEquals(deck.getHeader(), copy.getHeader());
        assertEquals(deck.getDescription(), copy.getDescription());
        assertEquals(deck.getPracticeCount(), copy.getPracticeCount());
        assertEquals(deck.getFlashcardCount(), copy.getFlashcardCount());
        assertTrue(copy.getFlashcards().containsAll(deck.getFlashcards()));
    }

    /**
     * Verifies private shuffle mutates the existing list in-place.
     */
    @Test
    void shuffleMutatesExistingListInPlace() throws Exception {
        Deck deck = new Deck("Biology");
        Flashcard one = new Flashcard("Cell", "Basic unit of life");
        Flashcard two = new Flashcard("Atom", "Basic unit of matter");
        Flashcard three = new Flashcard("Molecule", "Two or more atoms bonded");

        deck.addFlashcard(one);
        deck.addFlashcard(two);
        deck.addFlashcard(three);

        var originalListReference = deck.getFlashcards();
        Method shuffleMethod = Deck.class.getDeclaredMethod("shuffle");
        shuffleMethod.setAccessible(true);
        shuffleMethod.invoke(deck);

        assertSame(originalListReference, deck.getFlashcards());
        assertEquals(3, deck.getFlashcardCount());
        assertTrue(deck.getFlashcards().contains(one));
        assertTrue(deck.getFlashcards().contains(two));
        assertTrue(deck.getFlashcards().contains(three));
    }
}