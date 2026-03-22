package fi.jyu.ohj2.sourander.ankea.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import fi.jyu.ohj2.sourander.ankea.repository.DeckRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link DeckRepository}.
 */
class DeckRepositoryTest {

    private Deck spanishDeck;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck("Spanish Basics", "Hello world stuff");
        deck.setPracticeCount(2);

        Flashcard flashcard = new Flashcard("hola", "hello");
        flashcard.setViewCount(3);
        deck.addFlashcard(flashcard);
        this.spanishDeck = deck;
    }

    /**
     * Verifies saving decks creates a JSON file.
     */
    @Test
    void saveAllWritesJsonFile(@TempDir Path tempDir) throws IOException {
        Path databaseFile = tempDir.resolve("database.json");
        DeckRepository repository = new DeckRepository(databaseFile);

        repository.saveAll(List.of(spanishDeck));

        assertTrue(Files.exists(databaseFile));
        assertTrue(Files.size(databaseFile) > 0);
    }

    /**
     * Verifies saving replaces existing JSON content.
     */
    @Test
    void saveAllOverwritesExistingJson(@TempDir Path tempDir) throws IOException {
        Path databaseFile = tempDir.resolve("database.json");
        DeckRepository repository = new DeckRepository(databaseFile);

        repository.saveAll(List.of(spanishDeck));

        Deck replacementDeck = new Deck("History", "European capitals");
        repository.saveAll(List.of(replacementDeck));

        String json = Files.readString(databaseFile);
        assertTrue(json.contains("History"));
        assertFalse(json.contains("Spanish Basics"));
    }

    /**
     * Verifies loading from a missing file returns an empty list.
     */
    @Test
    void loadAllReturnsEmptyListWhenFileDoesNotExist(@TempDir Path tempDir) {
        DeckRepository repository = new DeckRepository(tempDir.resolve("database.json"));

        List<Deck> decks = repository.loadAll();

        assertTrue(decks.isEmpty());
    }

    /**
     * Verifies saving and loading restores decks, flashcards, and counters.
     */
    @Test
    void loadAllRestoresDecksFromJson(@TempDir Path tempDir) {
        Path databaseFile = tempDir.resolve("database.json");
        DeckRepository repository = new DeckRepository(databaseFile);
        Deck expectedDeck = spanishDeck;

        repository.saveAll(List.of(expectedDeck));

        List<Deck> loadedDecks = repository.loadAll();

        assertEquals(1, loadedDecks.size());

        Deck loadedDeck = loadedDecks.getFirst();
        assertEquals("Spanish Basics", loadedDeck.getHeader());
        assertEquals("Hello world stuff", loadedDeck.getDescription());
        assertEquals(2, loadedDeck.getPracticeCount());
        assertEquals(1, loadedDeck.getFlashcardCount());

        Flashcard loadedFlashcard = loadedDeck.getFlashcards().getFirst();
        assertEquals("hola", loadedFlashcard.getFront());
        assertEquals("hello", loadedFlashcard.getBack());
        assertEquals(3, loadedFlashcard.getViewCount());
    }
}