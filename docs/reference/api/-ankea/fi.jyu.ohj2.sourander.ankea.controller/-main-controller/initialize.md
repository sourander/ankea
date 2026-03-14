//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.controller](../index.md)/[MainController](index.md)/[initialize](initialize.md)

# initialize

[JVM]\
open fun [initialize](initialize.md)(location: [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html), resources: [ResourceBundle](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html))

Called by the JavaFX runtime after all `@FXML` fields have been injected. 

Creates a new [AnkeaModel](../../fi.jyu.ohj2.sourander.ankea.model/-ankea-model/index.md), sets the initial label text, and registers a click handler on testButton that updates testLabel with the model's response.

#### Parameters

JVM

| | |
|---|---|
| location | the URL of the FXML document (may be `null`) |
| resources | the resource bundle for the root object (may be `null`) |