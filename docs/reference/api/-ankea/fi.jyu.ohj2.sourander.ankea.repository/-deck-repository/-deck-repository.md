//[Ankea](../../../index.md)/[fi.jyu.ohj2.sourander.ankea.repository](../index.md)/[DeckRepository](index.md)/[DeckRepository](-deck-repository.md)

# DeckRepository

[JVM]\
constructor()

Creates a repository that persists data at the default location (`~/.ankea/database.json`).

[JVM]\
constructor(filePath: [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html))

Creates a repository that persists data at a custom location. This is handy for tests.

#### Parameters

JVM

| | |
|---|---|
| filePath | the path to the JSON file; parent directories are created automatically on the first save |