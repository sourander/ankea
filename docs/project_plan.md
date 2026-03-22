# Project Plan

## Functional Requirements

These are the requirements set by the course instructor. They are the minimum requirements that must be met for the project to be considered complete. I've simply translated them from Finnish to English. As written before, Ankea is a simple flashcard application inspired by Anki.

* `UR-1`: ✅ The user can create flashcard decks that contain cards. A deck has a name and an optional description.
* `UR-2`: ✅ The user can add cards to a flashcard deck. A card has a term and an explanation (front and back side).
* `UR-3`: ✅ The user can browse and edit the added flashcard decks.
* `UR-4`: ✅ The user can practice the cards of a deck in a "practice mode". In practice mode, the user is shown the front side of a single card. The user can view the explanation of the card by "flipping" the card. After that, the user can move to the next or previous card.
* `UR-5`: ✅ In practice mode, the cards are always displayed in a random order.
* `UR-6`: ✅ The user can edit and delete flashcard decks or their individual cards.


!!! note "Implementation Note (UR-4 & UR-5):"

    To satisfy both the random order requirement and the ability to move backward to the *previous* card, the application will use a "Session State" approach. When a Practice Session begins, the a new copy of the deck's card list it created, shuffled, and used during the session. This pretty much makes it so that during practice, one cannot edit cards. A new practice must be started to see the changes in cards.

### Extension Requirements (Nice-to-haves)

The Game Stats and Exam Mode features are not required for the project, but are set by the course instructor as optional extension requirements. If I end up adding any of my own, I will add them under a separate "Additional Extension Requirements" heading.

#### Game Stats

* `ER-1`: ✅ Add a view count for each card. Each time the user reveals a card's explanation in practice mode, the card's view count increases by one.
* `ER-2`: ✅ Card view counts are shown in the card table in the deck edit view.
* `ER-3`: ✅ Add a practice count for each deck. Each time the user opens practice mode and practices every card in the deck once, the deck's practice count increases by one.
* `ER-4`: ✅ Deck practice counts are shown in the main view as a separate column.

#### Exam Mode

* `ER-5`: Add an exam mode for decks. In exam mode, the user is shown the front of one card and three possible explanations as a multiple-choice question. The user must choose the explanation that matches the term on the front. The user is then shown the correct answer after which the next multiple-choice question is displayed.
* `ER-6`: Exam mode must work equally well for a deck with three cards and for a deck with several hundred cards.
* `ER-7`: Exam mode can only be entered if the deck contains at least three cards.
* `ER-8`: RadioButton and ToggleGroup components may be useful for implementing exam mode.

#### Additional Extension Requirements

* `OWN-9`: ✅ DeckRepository class handles saving and loading decks to and from the file system. JSON format, Jackson library, just like in course lesson material. Should be called when exiting any Add/Edit mode or after clicking any Delete button.
* `OWN-10`: ✅ The Practice mode can be played without mouse. Arrow keys are Prev/Next and Spacebar is Flip.

## Class Diagram (roughly also the Data Model)

This is a copy from the course material (translated to English). I have added the counter fields and the DeckRepository. The DeckRepository is not "data", so it will be placed into `./repostiory` package instead of `./model`.

```mermaid
classDiagram
    class DeckRepository {
        -String filePath
        +saveAll(List~Deck~ decks)
        +loadAll() List~Deck~
    }

    class DeckManager {
        -DeckRepository repository
        +List~Deck~ decks
        +addDeck(Deck deck)
        +removeDeck(Deck deck)
        +saveChanges()
    }

    class Deck {
        +String header
        +String description
        +List~Flashcard~ flashcards
        +int practiceCount
    }

    class Flashcard {
        +String front
        +String back
        +int viewCount
    }

    DeckManager "1" --> "1" DeckRepository : uses
    DeckManager "1" --> "0..*" Deck : manages
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
        Cards
        Stats{{🚧 Deck statistics}}
    Cards
        Front
        Back
        Stats{{🚧 Card statistics}}
    Study Modes
        Practice
            Practice session
        Exam{{🚧 Exam}}
            Exam session[🚧 Exam session]
            ExamAltAnswers[🚧 Distractors]
            ExamChoice[🚧 Question Setup]
    Statistics{{🚧 Statistics}}
        CardViews{{🚧 Card view counts}}
        DeckPrac{{🚧 Deck practice counts}}
```

### Workflow Model - Levels 1 & 2

The `View statictics` has been omitted from this Level 2 diagram. I don't believe I will have to implement it. Also, this diagram contains the "Level 2" details directly, since the app is so simple that it doesn't make sense to split the workflow into multiple abstraction levels.

```mermaid
stateDiagram-v2
    classDef extension fill:#ff9900,stroke:#333,stroke-width:2px,color:black;

    [*] --> Dashboard
    
    Dashboard: List of Decks
    Dashboard --> ActiveDeck: Add, Edit or Select a Deck

    state ActiveDeck {
        DeckMenu: Deck Chosen
        [*] --> DeckMenu
        
        %% Management Flow
        DeckMenu --> CardManagement: Manage Cards
        state CardManagement {
            [*] --> CardList: View Cards
            CardList --> AddCard: + New Card
            CardList --> EditCard: Modify/Delete
        }
        
        %% Practice Flow
        DeckMenu --> PracticeSession: Start Practice
        state PracticeSession {
            [*] --> FreeformPractice
            FreeformPractice --> [*]: Leave practice
        }
        
        %% Exam Flow (Extension)
        DeckMenu --> ExamSession: Start Exam
        state ExamSession {
            [*] --> TakeExam
            TakeExam --> [*]: Finish exam
        }
    }
    
    class ExamSession extension
```

!!! note

    Craig's Data Model has been omitted. The ER diagram above should be enough to cover the topic in this simple project.

## User Interface Design

My initial idea is to borrow the concept of **Pages** from DaVinci Resolve Studio, a video editing and color grading software. The main view of the application will be the Dashboard, which shows all the decks and their practice counts. From there, the user can either create a new deck or select an existing deck to manage or study. The same feature is called *Import, Edit, and Export Workflow* in Adobe Premiere Pro (see in action: [Closer Look at the New Import, Edit, and Export Workflow in Premiere Pro](https://www.4kshooters.net/2021/07/08/closer-look-at-the-new-import-edit-and-export-workflow-in-premiere-pro/)).

During assignment, we are forced to use JavaFX, so my first think to do was to make sure that there is some form of support for this feature. I found this tutorialspoint article which confirms it: [JavaFX - TabPane](https://www.tutorialspoint.com/javafx/javafx_tabpane.htm).

![](images/user_interface.svg)

**Diagram 1:** Initial UI design. The main view is the Dashboard, which is shown in the 3 first frames. Each frame shows a different tab. The last frame shows the Practice View, which is a popup that is shown when the user clicks the "Practice" button in the Study Room tab. Open the image in a new tab to see the details.

Idea is that the user will never have to explicitly **Save** anything. Observer pattern will be used to automatically update the data model and save the changes whenever the user makes any changes to the decks or cards. Obviously, in real life, there would have to be some sort of Undo feature, or version history, but that is out of scope for this project.

![](images/user_interface_popups.svg)

**Diagram 2:** When the user chooses New or Edit, a new popup window is shown. The underlying Dashboard cannot be interacted with until the popup is closed. The user can either save the changes by clicking the "Save" button, or discard the changes by clicking the "Cancel" button or closing the window. The pop-up on the left side is for creating (or editing) a Deck, the right one is for doing the same for a Flashcard.