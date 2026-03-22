//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.model](../index.md)/[Deck](index.md)

# Deck

[JVM]\
open class [Deck](index.md)

Represents a flashcard deck with metadata, cards, and practice statistics.

## Constructors

| | |
|---|---|
| [Deck](-deck.md) | [JVM]<br>constructor()<br>Creates an empty deck.<br>constructor(header: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Creates a deck with a header.<br>constructor(header: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), description: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Creates a deck with a header and description. |

## Properties

| Name | Summary |
|---|---|
| [DEFAULT_HEADER](-d-e-f-a-u-l-t_-h-e-a-d-e-r.md) | [JVM]<br>val [DEFAULT_HEADER](-d-e-f-a-u-l-t_-h-e-a-d-e-r.md): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) = &quot;This one has no name&quot;<br>Default header used when a deck is created without a user-provided name. |

## Functions

| Name | Summary |
|---|---|
| [addFlashcard](add-flashcard.md) | [JVM]<br>open fun [addFlashcard](add-flashcard.md)(flashcard: [Flashcard](../-flashcard/index.md))<br>Adds a flashcard to the deck. |
| [clearFlashcards](clear-flashcards.md) | [JVM]<br>open fun [clearFlashcards](clear-flashcards.md)()<br>Removes all flashcards from the deck. |
| [descriptionProperty](description-property.md) | [JVM]<br>open fun [descriptionProperty](description-property.md)(): StringProperty<br>Returns the deck description property. |
| [flashcardsProperty](flashcards-property.md) | [JVM]<br>open fun [flashcardsProperty](flashcards-property.md)(): ListProperty&lt;Flashcard&gt;<br>Returns the flashcards list property. |
| [getDescription](get-description.md) | [JVM]<br>open fun [getDescription](get-description.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Returns the deck description. |
| [getFlashcardCount](get-flashcard-count.md) | [JVM]<br>open fun [getFlashcardCount](get-flashcard-count.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Returns the number of flashcards in the deck. |
| [getFlashcards](get-flashcards.md) | [JVM]<br>open fun [getFlashcards](get-flashcards.md)(): ObservableList&lt;Flashcard&gt;<br>Returns the observable list of flashcards. |
| [getHeader](get-header.md) | [JVM]<br>open fun [getHeader](get-header.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Returns the deck header. |
| [getPracticeCardCount](get-practice-card-count.md) | [JVM]<br>open fun [getPracticeCardCount](get-practice-card-count.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Returns the number of cards available in the current practice session. |
| [getPracticeCount](get-practice-count.md) | [JVM]<br>open fun [getPracticeCount](get-practice-count.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Returns the practice count. |
| [getPracticeFlashcard](get-practice-flashcard.md) | [JVM]<br>open fun [getPracticeFlashcard](get-practice-flashcard.md)(practiceIndex: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)): [Flashcard](../-flashcard/index.md)<br>Returns the flashcard shown at the given practice-session position. |
| [headerProperty](header-property.md) | [JVM]<br>open fun [headerProperty](header-property.md)(): StringProperty<br>Returns the deck header property. |
| [incrementPracticeCount](increment-practice-count.md) | [JVM]<br>open fun [incrementPracticeCount](increment-practice-count.md)()<br>Increments the practice count by one. |
| [practiceCountProperty](practice-count-property.md) | [JVM]<br>open fun [practiceCountProperty](practice-count-property.md)(): IntegerProperty<br>Returns the practice count property. |
| [removeFlashcard](remove-flashcard.md) | [JVM]<br>open fun [removeFlashcard](remove-flashcard.md)(flashcard: [Flashcard](../-flashcard/index.md)): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)<br>Removes a flashcard from the deck. |
| [setDescription](set-description.md) | [JVM]<br>open fun [setDescription](set-description.md)(description: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Updates the deck description. |
| [setFlashcards](set-flashcards.md) | [JVM]<br>open fun [setFlashcards](set-flashcards.md)(flashcards: ObservableList&lt;Flashcard&gt;)<br>Replaces the flashcards list content. |
| [setHeader](set-header.md) | [JVM]<br>open fun [setHeader](set-header.md)(header: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Updates the deck header. |
| [setPracticeCount](set-practice-count.md) | [JVM]<br>open fun [setPracticeCount](set-practice-count.md)(practiceCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))<br>Updates the practice count. |
| [startPracticeSession](start-practice-session.md) | [JVM]<br>open fun [startPracticeSession](start-practice-session.md)()<br>Starts a new practice session by shuffling the display order of the cards. |
| [toString](to-string.md) | [JVM]<br>open fun [toString](to-string.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |