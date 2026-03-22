package fi.jyu.ohj2.sourander.ankea.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Flashcard}.
 */
class FlashcardTest {

    /**
     * Verifies default property values.
     */
    @Test
    void defaultConstructorCreatesEmptyFlashcard() {
        Flashcard flashcard = new Flashcard();

        assertEquals("", flashcard.getFront());
        assertEquals("", flashcard.getBack());
        assertEquals(0, flashcard.getViewCount());
    }

    /**
     * Verifies the convenience constructor and property accessors.
     */
    @Test
    void constructorAndSettersUpdateProperties() {
        Flashcard flashcard = new Flashcard("Term", "Explanation");

        assertEquals("Term", flashcard.getFront());
        assertEquals("Explanation", flashcard.getBack());

        flashcard.setViewCount(3);

        assertEquals(3, flashcard.getViewCount());
    }

    /**
     * Verifies that the view count helper increments correctly.
     */
    @Test
    void incrementViewCountIncreasesCounter() {
        Flashcard flashcard = new Flashcard();

        flashcard.incrementViewCount();
        flashcard.incrementViewCount();

        assertEquals(2, flashcard.getViewCount());
    }

    /** Verifies that null and blank values are rejected for front and back. */
    @Test
    void frontAndBackRejectNullAndBlank() {
        Flashcard flashcard = new Flashcard("Term", "Explanation");

        assertThrows(IllegalArgumentException.class, () -> flashcard.setFront(null));
        assertThrows(IllegalArgumentException.class, () -> flashcard.setFront(""));
        assertThrows(IllegalArgumentException.class, () -> flashcard.setFront("   "));
        assertThrows(IllegalArgumentException.class, () -> flashcard.setBack(null));
        assertThrows(IllegalArgumentException.class, () -> flashcard.setBack(""));
        assertThrows(IllegalArgumentException.class, () -> flashcard.setBack("   "));
        assertThrows(IllegalArgumentException.class, () -> new Flashcard(null, "Explanation"));
        assertThrows(IllegalArgumentException.class, () -> new Flashcard("Term", null));
    }

    /* Verifies that the Flashcard front side is trimmed of leading and trailing whitespace  */
    @Test
    void frontIsTrimmed() {
        Flashcard flashcard = new Flashcard("   Term   ", "Explanation");
        assertEquals("Term", flashcard.getFront());
        flashcard.setFront("   New Term   ");
        assertEquals("New Term", flashcard.getFront());
    }
}