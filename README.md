# Ankea

## What?

This is **Ankea**, a crappy version of its namesake, Anki. It is a flashcard program to help you learn and remember things from a chosen deck of flashcards. It is written in JAVA and uses JavaFX for the GUI. The work is a university course project for **Ohjelmointi 2** at University of Jyväskylä.

## Why VS Code?

During the course, we used IntelliJ IDEA as our IDE. I wanted to try out VS Code for this project, assuming that I will need to solve some Maven and JavaFX related issues that IntelliJ automagically solved for me previously.

## Prerequisites

- Java 25
- Maven
- Just
- uv (for running Zensical docs)
- xq (for XML prettying)

## Docs

You can find the docs for this project in the `docs` folder. They are also published to the GitHub Pages at [sourander.github.io/ankea](https://sourander.github.io/ankea/).

## Use of AI tools

All *production code* is written by me, Jani, manually. Language models have been used to:

* Generate project scaffolding (pom.xml, JavaFX setup, etc.)
* Help in translations and documentation writing (incl. javadocs)
* Finding bugs that I cannot solve myself within a reasonable time frame
* Figure out alternative approaches. I am Python-native. I use LLMs to detect the *Pythonic* style in my Java code and suggest Java-idiomatic alternatives.

Reason for translating everything to English is that I'm accustomed to writing code and documentation in English. Finnish variable names feel absolutely heretical to me.
