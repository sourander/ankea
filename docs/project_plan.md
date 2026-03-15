# Project Plan

## Functional Requirements

These are the requirements set by the course instructor. They are the minimum requirements that must be met for the project to be considered complete. I've simply translated them from Finnish to English. As written before, Ankea is a simple flashcard application inspired by Anki.

* `UR-1`: The user can create flashcard decks that contain cards. A deck has a name and an optional description.
* `UR-2`: The user can add cards to a flashcard deck. A card has a term and an explanation (front and back side).
* `UR-3`: The user can browse and edit the added flashcard decks.
* `UR-4`: The user can practice the cards of a deck in a "practice mode". In practice mode, the user is shown the front side of a single card. The user can view the explanation of the card by "flipping" the card. After that, the user can move to the next or previous card.
* `UR-5`: In practice mode, the cards are always displayed in a random order.
* `UR-6`: The user can edit and delete flashcard decks or their individual cards.

### Extension Requirements (Nice-to-haves)

The Game Stats and Exam Mode features are not required for the project, but are set by the course instructor as optional extension requirements. If I end up adding any of my own, I will add them under a separate "Additional Extension Requirements" heading.

#### Game Stats

* `ER-1`: Add a view count for each card. Each time the user reveals a card's explanation in practice mode, the card's view count increases by one.
* `ER-2`: Card view counts are shown in the card table in the deck edit view.
* `ER-3`: Add a practice count for each deck. Each time the user opens practice mode and practices every card in the deck once, the deck's practice count increases by one.
* `ER-4`: Deck practice counts are shown in the main view as a separate column.

#### Exam Mode

* `ER-5`: Add an exam mode for decks. In exam mode, the user is shown the front of one card and three possible explanations as a multiple-choice question. The user must choose the explanation that matches the term on the front. The user is then shown the correct answer after which the next multiple-choice question is displayed.
* `ER-6`: Exam mode must work equally well for a deck with three cards and for a deck with several hundred cards.
* `ER-7`: Exam mode can only be entered if the deck contains at least three cards.
* `ER-8`: RadioButton and ToggleGroup components may be useful for implementing exam mode.

#### Additional Extension Requirements

None yet.

## Class Diagram (Data Model)

This is a copy from the course material (translated to English). I will need to plan the methods and other paarameters later. Also, a separate class to handle the materialized Deck repository might be handy.

```mermaid
classDiagram
	class DeckManager {
		+List~Deck~ decks
	}

	class Deck {
		+String header
		+String description
		+List~Flashcard~ flashcards
	}

	class Flashcard {
		+String front
		+String back
	}

	DeckManager "1" --> "0..*" Deck : contains
	Deck "1" --> "0..*" Flashcard : contains
```

## Design Models

This design pattern is from Craig Erray's *"Design for Complex Software Systems"* (Pearson / O'Reilly) live training from 2025.

**NOTE:** Gemini Pro 3.1 was used to help me with Mermaid syntax. This saved precious time. Design choices are all mine, though.

### Domain Model - Level 1

The Mermaid Mindmap does not support the classDef syntax: *"These classes need to be supplied by the site administrator."* ([source](https://mermaid.ai/open-source/syntax/mindmap.html#classes)). I will use the Hexagon shape and the 🚧 emoji to highlight the extension requirements.


```mermaid
mindmap
  root((Ankea))
    Decks
    Cards
    Study Modes
    Statistics{{🚧 Statistics}}
```

### Domain Model - Level 2

```mermaid
mindmap
  root((Ankea))
    Decks
        Browse decks
        Create new deck
        Edit deck details
        Delete deck
    Cards
        Add new card
        Edit front or back
        Delete card
    Study Modes
        Practice Mode
            Randomized order
            Flip card
            Navigate next and prev
        ExamMode{{🚧 Exam Mode}}
            ExamRand[🚧 Randomized order]
            ExamNav[🚧 Navigate next]
            ExamChoice[🚧 Multi-choice]
    Statistics{{🚧 Statistics}}
        CardViews{{🚧 Card view counts}}
        DeckPrac{{🚧 Deck practice counts}}
```

### Workflow Model - Level 1

```mermaid
stateDiagram-v2
    classDef extension fill:#ff9900,stroke:#333,stroke-width:2px,color:black;

    state main_choice <<choice>>
    state study_choice <<choice>>
    
    Options: What to do?
    StudyMode: Select study type

    [*] --> Dashboard
    
    Dashboard --> main_choice: User action
    main_choice --> Dashboard: Create new deck
    main_choice --> Options: Select existing deck

    Options --> DeckEditor: Edit deck / manage cards
    Options --> StudyMode: Start studying

    StudyMode --> study_choice
    study_choice --> Practice: Choose Practice Mode
    study_choice --> Exam: Choose Exam Mode

    class Exam extension
```

### Workflow Model - Level 2

```mermaid
stateDiagram-v2
    classDef extension fill:#ff9900,stroke:#333,stroke-width:2px,color:black;

    state options_choice <<choice>>
    state edit_choice <<choice>>
    state study_choice <<choice>>

    Options: What to do with the selected deck?

    [*] --> Options
    Options --> options_choice

    %% -------------------------------
    %% BRANCH 1: DECK EDITOR
    %% -------------------------------
    options_choice --> DeckEditor: Manage deck

    DeckEditor: Deck & Card List
    DeckEditor --> edit_choice: Select action

    edit_choice --> EditDeck: Edit deck details

    edit_choice --> DeleteDeck: Delete deck
    edit_choice --> AddCard: Add new card
    edit_choice --> EditCard: Edit front/back
    edit_choice --> DeleteCard: Delete card

    %% -------------------------------
    %% BRANCH 2: STUDY MODES
    %% -------------------------------
    options_choice --> StudyMode: Start studying

    StudyMode: Select study type
    StudyMode --> study_choice


    %% --- Practice Mode Sub-branch ---
    study_choice --> InitPractice: Practice Mode

    InitPractice: Randomize card order
    InitPractice --> PracticeTerm
    PracticeTerm: Show front
    PracticeTerm --> PracticeFlip: User flips card

    PracticeFlip: Show back
    PracticeFlip --> EndPractice: Finish deck
    EndPractice: Increment practice count

    %% --- Exam Mode Sub-branch ---
    study_choice --> InitExam: Exam Mode

    InitExam: Randomize order
    InitExam --> ExamQuestion

    ExamQuestion: Show front & choices
    ExamQuestion --> ExamAnswer: User selects option
    ExamAnswer: Show feedback
    ExamAnswer --> EndExam: Finish exam

    class ExamQuestion, ExamAnswer, EndExam extension
    class EndPractice extension
```


### Data Model - Level 1

```mermaid
flowchart TD
    DeckManager -- have --> Deck
    Deck -- have --> Flashcard
```

### Data Model - Level 2

```mermaid
flowchart TD
    classDef extension fill:#ff9900,stroke:#333,stroke-width:2px,color:black;

    DeckManager[Deck Manager]
    
    DeckManager -- manages --> Decks[Decks]
    
    Decks -- have --> DeckDetails[Deck Details]
    Decks -- track --> DeckStats[Practice Counts]:::extension
    
    Decks -- contain --> Flashcards[Flashcards]
    Decks -- initiate --> Sessions[Study Sessions]:::extension
    
    Flashcards -- have --> CardContent[Front & Back]
    Flashcards -- track --> CardStats[View Counts]:::extension
    
    %% Cross-link showing how cards feed into the sessions
    Flashcards -- populate --> Sessions
    
    Sessions -- have --> PracMode[Practice Mode Data]:::extension
    Sessions -- have --> ExamMode[Exam Mode Data]:::extension
    
    ExamMode -- generate --> Choices[Multiple Choice Options]:::extension
    ExamMode -- result in --> Feedback[Answer Feedback]:::extension
```

## User Interface Design

TODO!
