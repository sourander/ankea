package fi.jyu.ohj2.sourander.ankea.repository;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.Flashcard;

/**
 * Persistence layer that loads and saves all decks to a JSON file on disk.
 *
 * By default the data is stored in {@code ~/.ankea/database.json}. A custom
 * {@link java.nio.file.Path} can be supplied via the secondary constructor, which
 * is useful for testing purposes.
 * 
 * Because {@link Deck} and {@link Flashcard} use JavaFX Property types, they
 * are mapped through package-private DTOs ({@code DeckData} / {@code FlashcardData})
 * rather than being serialised directly.
 */
public class DeckRepository {
    /** Default path used when no custom location is provided. */
    private static final Path DEFAULT_FILE_PATH = Paths.get(
            System.getProperty("user.home"),
            ".ankea",
            "database.json"
    );

    /** File path used for reading and writing the JSON database. */
    private final Path filePath;

    /** Jackson mapper used for JSON serialisation and deserialisation. */
    private final ObjectMapper objectMapper;

    /**
     * Creates a repository that persists data at the default location
     * ({@code ~/.ankea/database.json}).
     */
    public DeckRepository() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Creates a repository that persists data at a custom location.
     * This is handy for tests.
     *
     * @param filePath the path to the JSON file; parent directories are created
     *                 automatically on the first save
     */
    public DeckRepository(Path filePath) {
        this.filePath = filePath;
        this.objectMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }

    /**
     * Serialises the given deck list to the JSON file.
     *
     * @param decks the decks to persist; may be {@code null}
     */
    public void saveAll(List<Deck> decks) {
        if (decks == null) {
            return;
        }

        List<DeckData> deckDataList = new ArrayList<>();

        for (Deck deck : decks) {
            DeckData data = toDeckData(deck);
            deckDataList.add(data);
        }

        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            objectMapper.writeValue(filePath.toFile(), deckDataList);
        } catch (IOException | RuntimeException e) {
            System.err.println("Failed to save decks: " + e.getMessage());
        }
    }

    /**
     * Deserialises all decks from the JSON file.
     *
     * @return the loaded decks or an empty list
     */
    public List<Deck> loadAll() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            DeckData[] deckData = objectMapper.readValue(filePath.toFile(), DeckData[].class);
            List<Deck> decks = new ArrayList<>();

            for (DeckData currentDeckData : deckData) {
                decks.add(toDeck(currentDeckData));
            }

            return decks;
        } catch (RuntimeException e) {
            System.err.println("Failed to load decks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Converts a {@link Deck} domain object to its serialisable DTO.
     *
     * @param deck the deck to convert
     * @return the corresponding {@link DeckData} DTO
     */
    private static DeckData toDeckData(Deck deck) {
        DeckData deckData = new DeckData();
        deckData.header = deck.getHeader();
        deckData.description = deck.getDescription();
        deckData.practiceCount = deck.getPracticeCount();

        for (Flashcard flashcard : deck.getFlashcards()) {
            FlashcardData flashcardData = new FlashcardData();
            flashcardData.front = flashcard.getFront();
            flashcardData.back = flashcard.getBack();
            flashcardData.viewCount = flashcard.getViewCount();
            deckData.flashcards.add(flashcardData);
        }

        return deckData;
    }

    /**
     * Converts a {@link DeckData} DTO back to a {@link Deck} domain object.
     *
     * @param deckData the DTO to convert
     * @return the reconstructed {@link Deck}
     */
    private static Deck toDeck(DeckData deckData) {
        Deck deck = new Deck(deckData.header == null || deckData.header.isBlank()
                ? Deck.DEFAULT_HEADER
                : deckData.header);
        deck.setDescription(deckData.description);
        deck.setPracticeCount(deckData.practiceCount);

        for (FlashcardData flashcardData : deckData.flashcards) {
            Flashcard flashcard = new Flashcard(flashcardData.front, flashcardData.back);
            flashcard.setViewCount(flashcardData.viewCount);
            deck.addFlashcard(flashcard);
        }

        return deck;
    }

    /** DTO (Data Transfer Object) used to serialise and deserialise a single {@link Deck}. 
     * 
     * This is a bit like Pydantic model in Python, but without any validation or immutability features.
    */
    private static final class DeckData {
        public String header;
        public String description;
        public List<FlashcardData> flashcards = new ArrayList<>();
        public int practiceCount;
    }

    /** DTO for a {@link Flashcard}. */
    private static final class FlashcardData {
        public String front;
        public String back;
        public int viewCount;
    }
}