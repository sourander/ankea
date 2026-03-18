//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.controller](../index.md)/[MainController](index.md)/[initialize](initialize.md)

# initialize

[JVM]\
open fun [initialize](initialize.md)(location: [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html), resources: [ResourceBundle](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html))

Called by the JavaFX runtime after all `@FXML` fields have been injected. Creates a new [DeckManager](../../fi.jyu.ohj2.sourander.ankea.model/-deck-manager/index.md). Does some funky demo stuff. Will be extended to load real decks and set up the actual UI when... it is time.

#### Parameters

JVM

| | |
|---|---|
| location | the URL of the FXML document |
| resources | the resource bundle for the root object |