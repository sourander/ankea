// src/main/java/fi/jyu/ohj2/sourander/ankea/controller/MainController.java
package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.App;
import fi.jyu.ohj2.sourander.ankea.model.Deck;
import fi.jyu.ohj2.sourander.ankea.model.DeckManager;
import fi.jyu.ohj2.sourander.ankea.repository.DeckRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX controller for the main application view ({@code main.fxml}).
 *
 * Sets up event handlers. Very observe. Such wow.
 */
public class MainController implements Initializable {

    /** The table displaying all decks. */
    @FXML
    private TableView<Deck> decksTable;

    /** The main tab pane containing all 3 tabs. */
    @FXML
    private TabPane mainTabPane;

    @FXML
    private Button addDeckButton;

    @FXML
    private Button editDeckButton;

    @FXML
    private Button deleteDeckButton;

    /** The DeckManager itself. */
    private DeckManager model;

    /**
     * Repository responsible for persisting decks to disk.
     * the decks on app startup.
     */
    private final DeckRepository repository = new DeckRepository();

    /**
     * Initialises the controller: loads decks from disk, sets up table columns,
     * row factory, and button handlers.
     *
     * @param location  the URL of the FXML document
     * @param resources the resource bundle for the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new DeckManager();
        model.getDecks().addAll(repository.loadAll());

        decksTable.setItems(model.getDecks());
        setupDecksTableColumns();

        decksTable.setRowFactory(tv -> {
            TableRow<Deck> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && !row.isEmpty()) {
                    editDeck(row.getItem());
                }
            });
            return row;
        });

        addDeckButton.setOnAction(event -> addDeck());
        editDeckButton.setOnAction(event -> editDeck(decksTable.getSelectionModel().getSelectedItem()));
        deleteDeckButton.setOnAction(event -> deleteDeck());

        mainTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            switch (mainTabPane.getTabs().indexOf(newTab)) {
                case 0 -> System.out.println("User returned to " + newTab.getText() + "!");
                case 1 -> System.out.println("User switched to " + newTab.getText() + "!");
                case 2 -> System.out.println("User switched to " + newTab.getText() + "!");
            }
        });
    }

    /** Configures the columns of {@code decksTable} and binds the description column width. */
    private void setupDecksTableColumns() {
        TableColumn<Deck, String> nameCol = new TableColumn<>("Deck name");
        nameCol.setCellValueFactory(cd -> cd.getValue().headerProperty());

        TableColumn<Deck, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(cd -> cd.getValue().descriptionProperty());

        TableColumn<Deck, Number> countCol = new TableColumn<>("# of cards");
        countCol.setCellValueFactory(cd -> cd.getValue().flashcardsProperty().sizeProperty());

        TableColumn<Deck, Number> practiceCol = new TableColumn<>("Practice count");
        practiceCol.setCellValueFactory(cd -> cd.getValue().practiceCountProperty());

        decksTable.getColumns().add(nameCol);
        decksTable.getColumns().add(descCol);
        decksTable.getColumns().add(countCol);
        decksTable.getColumns().add(practiceCol);

        descCol.prefWidthProperty().bind(
            decksTable.widthProperty()
            .subtract(nameCol.widthProperty())
            .subtract(countCol.widthProperty())
            .subtract(practiceCol.widthProperty())
            .subtract(2)
        );
    }

    /** Opens the add-deck dialog and, if saved, adds the new deck to the model and persists it. */
    private void addDeck() {
        Deck newDeck = new Deck();
        EditDeckController ctrl = openEditDeckWindow(newDeck, "Add deck");
        if (ctrl != null && ctrl.isSaved()) {
            model.addDeck(newDeck);
            repository.saveAll(model.getDecks());
        }
    }

    /**
     * Opens the edit-deck dialog for the given deck and persists changes if saved.
     *
     * @param deck the deck to edit; does nothing if {@code null}
     */
    private void editDeck(Deck deck) {
        if (deck == null) return;
        EditDeckController ctrl = openEditDeckWindow(deck, "Edit deck");
        if (ctrl != null && ctrl.isSaved()) {
            repository.saveAll(model.getDecks());
        }
    }

    /** Removes the currently selected deck from the model and persists the change. */
    private void deleteDeck() {
        Deck selected = decksTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        model.removeDeck(selected);
        repository.saveAll(model.getDecks());
    }

    /**
     * Loads and displays the edit-deck dialog as a modal window.
     *
     * @param deck        the deck to pass to the dialog
     * @param titlePrefix prefix used in the window title
     * @return the {@link EditDeckController} after the dialog closes
     */
    private EditDeckController openEditDeckWindow(Deck deck, String titlePrefix) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("edit-deck.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            EditDeckController controller = loader.getController();
            controller.setDeck(deck);

            Stage dialogi = new Stage();
            dialogi.setScene(scene);
            dialogi.setTitle(titlePrefix + ": " + deck.getHeader());
            dialogi.setMinWidth(400);
            dialogi.setMinHeight(300);
            dialogi.initModality(Modality.APPLICATION_MODAL);
            dialogi.showAndWait();

            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
