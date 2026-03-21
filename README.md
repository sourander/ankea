# Ankea

## What?

This is **Ankea**, a crappy version of its namesake, Anki. It is a flashcard program to help you learn and remember things from a chosen deck of flashcards. It is written in JAVA and uses JavaFX for the GUI. The work is a university course project for **Ohjelmointi 2** at University of Jyväskylä.

## Docs

You can find the docs for this project in the `docs` folder. They are also published to the GitHub Pages at [sourander.github.io/ankea](https://sourander.github.io/ankea/).

## TLDR; I wanna run it

If you do not like reading docs, you can get started by:


## Prerequisites

Make sure you have following installed: Java 25, Maven, Just.

Then, run:

```bash
# To run tests
just test

# To build and launch the app
just run
```


## Use of AI tools

All *production code* is written by me, Jani, manually. Language models have been used to:

* Generate project scaffolding (pom.xml, JavaFX setup, etc.)
* Help in translations and documentation writing (incl. javadocs)
* Finding bugs that I cannot solve myself within a reasonable time frame
* Figure out alternative approaches. I am Python-native. I use LLMs to detect the *Pythonic* style in my Java code and suggest Java-idiomatic alternatives.

Reason for translating everything to English is that I'm accustomed to writing code and documentation in English. Finnish variable names feel absolutely heretical to me.
