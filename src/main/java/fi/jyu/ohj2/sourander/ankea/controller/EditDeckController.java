package fi.jyu.ohj2.sourander.ankea.controller;

import fi.jyu.ohj2.sourander.ankea.model.Deck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeckController implements Initializable {

    @FXML
    private TextField headerField;

    @FXML
    private TextArea descField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private Deck deck;
    private boolean saved = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(event -> {
            if (headerField.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation error");
                alert.setHeaderText(null);
                alert.setContentText("Deck name must not be blank.");
                alert.showAndWait();
                return;
            }
            deck.setHeader(headerField.getText());
            deck.setDescription(descField.getText());
            saved = true;
            closeWindow();
        });

        cancelButton.setOnAction(event -> closeWindow());
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        headerField.setText(deck.getHeader());
        descField.setText(deck.getDescription());
    }

    public boolean isSaved() {
        return saved;
    }

    private void closeWindow() {
        ((Stage) saveButton.getScene().getWindow()).close();
    }
}
