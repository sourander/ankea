//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.model](../index.md)/[DeckManager](index.md)

# DeckManager

[JVM]\
open class [DeckManager](index.md)

Root domain model that owns all decks in the application.

## Constructors

| | |
|---|---|
| [DeckManager](-deck-manager.md) | [JVM]<br>constructor()<br>Creates an empty deck manager. |

## Functions

| Name | Summary |
|---|---|
| [addDeck](add-deck.md) | [JVM]<br>open fun [addDeck](add-deck.md)(deck: [Deck](../-deck/index.md))<br>Adds a deck to the manager. |
| [clearDecks](clear-decks.md) | [JVM]<br>open fun [clearDecks](clear-decks.md)()<br>Removes all decks. |
| [decksProperty](decks-property.md) | [JVM]<br>open fun [decksProperty](decks-property.md)(): ListProperty&lt;Deck&gt;<br>Returns the decks list property. |
| [getDeckCount](get-deck-count.md) | [JVM]<br>open fun [getDeckCount](get-deck-count.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Returns the number of managed decks. |
| [getDecks](get-decks.md) | [JVM]<br>open fun [getDecks](get-decks.md)(): ObservableList&lt;Deck&gt;<br>Returns the observable list of decks. |
| [removeDeck](remove-deck.md) | [JVM]<br>open fun [removeDeck](remove-deck.md)(deck: [Deck](../-deck/index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Removes a deck from the manager. |
| [setDecks](set-decks.md) | [JVM]<br>open fun [setDecks](set-decks.md)(decks: ObservableList&lt;Deck&gt;)<br>Replaces the decks list content. |
| [toString](to-string.md) | [JVM]<br>open fun [toString](to-string.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |