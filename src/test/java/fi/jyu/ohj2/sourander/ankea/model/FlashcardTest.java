package fi.jyu.ohj2.sourander.ankea.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        flashcard.setFront(null);
        flashcard.setBack(null);
        flashcard.setViewCount(3);

        assertEquals("", flashcard.getFront());
        assertEquals("", flashcard.getBack());
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
}