package fi.jyu.ohj2.sourander.ankea.model;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void startPracticeSession_ShouldNotMutateDeck_AndProvideAllCards() {
        // Arrange
        Flashcard one = new Flashcard("bonjour", "hello");
        Flashcard two = new Flashcard("chat", "cat");
        Flashcard three = new Flashcard("chien", "dog");

        Deck deck = new Deck("French", "Basics");
        List<Flashcard> cards = List.of(one, two, three);
        cards.forEach(deck::addFlashcard);


        deck.startPracticeSession();

        // Verify original deck order is untouched
        assertThat(deck.getFlashcards()).containsExactly(one, two, three);

        // View count for whatever this first card is should increase from 0 -> 1; and then 1 -> 2
        Flashcard firstPracticeCard = deck.getPracticeFlashcard(0);
        Flashcard repeatedFirstPracticeCard = deck.getPracticeFlashcard(0);

        assertThat(repeatedFirstPracticeCard).isSameAs(firstPracticeCard);

        // Verify practice session contains the same card instances
        List<Flashcard> practiceCards = new ArrayList<>();

        // View count should increase for all, meaning (2->3, 0->1, 0->1)
        for (int i = 0; i < deck.getPracticeCardCount(); i++) {
            practiceCards.add(deck.getPracticeFlashcard(i));
        }

        assertThat(practiceCards).containsExactlyInAnyOrderElementsOf(cards);

        // Verify view counts
        assertThat(firstPracticeCard.getViewCount()).isEqualTo(3);
        assertThat(cards).filteredOn(card -> card != firstPracticeCard)
                .allSatisfy(card -> assertThat(card.getViewCount()).isEqualTo(1));
        assertThat(cards).extracting(Flashcard::getViewCount).containsExactlyInAnyOrder(3, 1, 1);
    }

    /* Verifies that the deck header is trimmed of leading and trailing whitespace */
    @Test
    void headerIsTrimmed() {
        Deck deck = new Deck("   French   ");
        assertEquals("French", deck.getHeader());
        deck.setHeader("   Advanced French   ");
        assertEquals("Advanced French", deck.getHeader());
    }
}
