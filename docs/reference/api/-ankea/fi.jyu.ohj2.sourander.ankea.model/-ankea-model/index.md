//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.model](../index.md)/[AnkeaModel](index.md)

# AnkeaModel

[JVM]\
open class [AnkeaModel](index.md)

The data model for the Ankea application. 

Tracks how many times the user has triggered a response and produces a human-readable status string on each call.

## Constructors

| | |
|---|---|
| [AnkeaModel](-ankea-model.md) | [JVM]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [generateResponse](generate-response.md) | [JVM]<br>open fun [generateResponse](generate-response.md)(): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)<br>Increments the internal click counter and returns a status message. |