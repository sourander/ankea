// src/main/java/fi/jyu/ohj2/sourander/ankea/model/AnkeaModel.java
package fi.jyu.ohj2.sourander.ankea.model;

public class AnkeaModel {
    private int clickCount = 0;

    public String generateResponse() {
        clickCount++;
        return "Model click count " + clickCount;
    }
}