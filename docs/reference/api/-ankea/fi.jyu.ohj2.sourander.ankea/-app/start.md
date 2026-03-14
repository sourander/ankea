//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea](../index.md)/[App](index.md)/[start](start.md)

# start

[JVM]\
open fun [start](start.md)(stage: Stage)

Initialises and displays the primary stage. 

Loads `main.fxml` from the same resource package as this class, wraps it in a 640 × 480 scene, and shows the stage.

#### Parameters

JVM

| | |
|---|---|
| stage | the primary stage provided by the JavaFX runtime |

#### Throws

| | |
|---|---|
| [IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) | if the FXML resource cannot be loaded |