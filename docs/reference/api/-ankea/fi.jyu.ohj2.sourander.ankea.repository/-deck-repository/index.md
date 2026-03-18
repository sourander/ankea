//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.repository](../index.md)/[DeckRepository](index.md)

# DeckRepository

[JVM]\
open class [DeckRepository](index.md)

Persistence layer that loads and saves all decks to a JSON file on disk. By default the data is stored in `~/.ankea/database.json`. A custom [java.nio.file.Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html) can be supplied via the secondary constructor, which is useful for testing purposes. Because [Deck](../../fi.jyu.ohj2.sourander.ankea.model/-deck/index.md) and [Flashcard](../../fi.jyu.ohj2.sourander.ankea.model/-flashcard/index.md) use JavaFX Property types, they are mapped through package-private DTOs (`DeckData` / `FlashcardData`) rather than being serialised directly.

## Constructors

| | |
|---|---|
| [DeckRepository](-deck-repository.md) | [JVM]<br>constructor()<br>Creates a repository that persists data at the default location (`~/.ankea/database.json`).<br>constructor(filePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html))<br>Creates a repository that persists data at a custom location. |

## Functions

| Name | Summary |
|---|---|
| [loadAll](load-all.md) | [JVM]<br>open fun [loadAll](load-all.md)(): [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;[Deck](../../fi.jyu.ohj2.sourander.ankea.model/-deck/index.md)&gt;<br>Deserialises all decks from the JSON file. |
| [saveAll](save-all.md) | [JVM]<br>open fun [saveAll](save-all.md)(decks: [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)&lt;[Deck](../../fi.jyu.ohj2.sourander.ankea.model/-deck/index.md)&gt;)<br>Serialises the given deck list to the JSON file. |