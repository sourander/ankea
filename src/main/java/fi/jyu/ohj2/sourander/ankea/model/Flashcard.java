package fi.jyu.ohj2.sourander.ankea.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a single flashcard with a front side, a back side, and a view count.
 */
public class Flashcard {

    /** The front side text shown to the user before flipping the card. */
    private final StringProperty front = new SimpleStringProperty("");

    /** The explanation shown after the card is flipped. */
    private final StringProperty back = new SimpleStringProperty("");

    /** Tracks how many times the card has been revealed in practice mode. */
    private final IntegerProperty viewCount = new SimpleIntegerProperty(0);

    /**
     * Creates an empty flashcard.
     */
    public Flashcard() {
        // Default constructor for JavaFX-friendly model creation.
    }

    /**
     * Creates a flashcard with the given front and back text.
     *
     * @param front the front side text
     * @param back  the back side text
     */
    public Flashcard(String front, String back) {
        setFront(front);
        setBack(back);
    }

    /**
     * Returns the front side text.
     *
     * @return the front side text
     */
    public String getFront() {
        return front.get();
    }

    /**
     * Updates the front side text.
     *
     * @param front the new front side text
     */
    public void setFront(String front) {
        this.front.set(front == null ? "" : front);
    }

    /**
     * Returns the front side property.
     *
     * @return the front side property
     */
    public StringProperty frontProperty() {
        return front;
    }

    /**
     * Returns the back side text.
     *
     * @return the back side text
     */
    public String getBack() {
        return back.get();
    }

    /**
     * Updates the back side text.
     *
     * @param back the new back side text
     */
    public void setBack(String back) {
        this.back.set(back == null ? "" : back);
    }

    /**
     * Returns the back side property.
     *
     * @return the back side property
     */
    public StringProperty backProperty() {
        return back;
    }

    /**
     * Returns the view count.
     *
     * @return the number of times the card has been revealed
     */
    public int getViewCount() {
        return viewCount.get();
    }

    /**
     * Updates the view count.
     *
     * @param viewCount the new view count
     */
    public void setViewCount(int viewCount) {
        this.viewCount.set(viewCount);
    }

    /**
     * Returns the view count property.
     *
     * @return the view count property
     */
    public IntegerProperty viewCountProperty() {
        return viewCount;
    }

    /**
     * Increments the card view count by one.
     */
    public void incrementViewCount() {
        setViewCount(getViewCount() + 1);
    }

    @Override
    public String toString() {
        return getFront();
    }
}