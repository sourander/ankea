# Ankea

## What?

This is **Ankea**, a crappy version of its namesake, Anki. It is a flashcard program to help you learn and remember things from a chosen deck of flashcards. It is written in JAVA and uses JavaFX for the GUI. The work is a university course project for **Ohjelmointi 2** at University of Jyväskylä.

## Why VS Code?

During the course, we used IntelliJ IDEA as our IDE. I wanted to try out VS Code for this project, assuming that I will need to solve some Maven and JavaFX related issues that IntelliJ automagically solved for me previously.

## Prerequisites

- Java 25
- Maven
- uv (for running Zensical docs)

### Installation of prerequisites

This project was developed on Linux. Other OSs have not been tested. To see all commands used to create and setup this project, refer to [History of the Creation of Ankea](SETUP.md). Sadly, the `"${userHome}/.sdkman/candidates/java/current"` did not work, nor did `"${env:JAVA_HOME}"`, so I had to hardcode the path to Java in the VS Code settings to `/home/sourander/.sdkman/candidates/java/current`. Not optimal, but it is what it is.