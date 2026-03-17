//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.model](../index.md)/[Flashcard](index.md)

# Flashcard

[JVM]\
open class [Flashcard](index.md)

Represents a single flashcard with a front side, a back side, and a view count.

## Constructors

| | |
|---|---|
| [Flashcard](-flashcard.md) | [JVM]<br>constructor()<br>Creates an empty flashcard.<br>constructor(front: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), back: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Creates a flashcard with the given front and back text. |

## Functions

| Name | Summary |
|---|---|
| [backProperty](back-property.md) | [JVM]<br>open fun [backProperty](back-property.md)(): StringProperty<br>Returns the back side property. |
| [frontProperty](front-property.md) | [JVM]<br>open fun [frontProperty](front-property.md)(): StringProperty<br>Returns the front side property. |
| [getBack](get-back.md) | [JVM]<br>open fun [getBack](get-back.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Returns the back side text. |
| [getFront](get-front.md) | [JVM]<br>open fun [getFront](get-front.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Returns the front side text. |
| [getViewCount](get-view-count.md) | [JVM]<br>open fun [getViewCount](get-view-count.md)(): [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html)<br>Returns the view count. |
| [incrementViewCount](increment-view-count.md) | [JVM]<br>open fun [incrementViewCount](increment-view-count.md)()<br>Increments the card view count by one. |
| [setBack](set-back.md) | [JVM]<br>open fun [setBack](set-back.md)(back: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Updates the back side text. |
| [setFront](set-front.md) | [JVM]<br>open fun [setFront](set-front.md)(front: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html))<br>Updates the front side text. |
| [setViewCount](set-view-count.md) | [JVM]<br>open fun [setViewCount](set-view-count.md)(viewCount: [Int](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/index.html))<br>Updates the view count. |
| [toString](to-string.md) | [JVM]<br>open fun [toString](to-string.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [viewCountProperty](view-count-property.md) | [JVM]<br>open fun [viewCountProperty](view-count-property.md)(): IntegerProperty<br>Returns the view count property. |