// src/main/java/fi/jyu/ohj2/sourander/ankea/model/AnkeaModel.java
package fi.jyu.ohj2.sourander.ankea.model;

/**
 * The data model for the Ankea application.
 *
 * <p>Tracks how many times the user has triggered a response and
 * produces a human-readable status string on each call.
 */
public class AnkeaModel {

    /** Running total of how many times {@link #generateResponse()} has been called. */
    private int clickCount = 0;

    /**
     * Increments the internal click counter and returns a status message.
     *
     * @return a string of the form {@code "Model click count N"} where
     *         {@code N} is the updated counter value
     */
    public String generateResponse() {
        clickCount++;
        return "Model click count " + clickCount;
    }
}