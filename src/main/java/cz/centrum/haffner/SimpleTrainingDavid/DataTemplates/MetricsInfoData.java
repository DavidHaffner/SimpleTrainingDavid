package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class MetricsInfoData {

    private int missingFieldsRowsCounter =0;        // Number of rows with missing fields
    private int blankContentMessagesCounter =0;     // Number of messages with blank content
    private int fieldsErrorsRowsCounter =0;        // Number of rows with fields errors
    private Map<Integer, Integer> groupedCallsOriginCounter = new HashMap();  // Number of calls origin grouped by country code
    private Map<Integer, Integer> groupedCallsDestinationCounter = new HashMap();  // Number of calls destination grouped by country code
    private float koToOkRatio =0.0f;               // Relationship between OK/KO calls
    private float averageCallDuration =0.0f;            // Average call duration TODO: grouped by country code
    private String givenWordsRanking ="UNDER CONSTRUCTION YET";  // TODO: Word occurrence ranking for the given words in message

    // increment by 1
    public void incrementMissingFieldsRowsCounter() {
        incrementMissingFieldsRowsCounter(1);
    }
    // increment by 1
    public void incrementMissingFieldsRowsCounter(int amountToAdd) {
        if (amountToAdd >0) {
            missingFieldsRowsCounter += amountToAdd;
        }
    }

    // increment by 1
    public void incrementBlankContentMessagesCounter() {
        incrementBlankContentMessagesCounter(1);
    }
    // increment by n
    public void incrementBlankContentMessagesCounter(int amountToAdd) {
        if (amountToAdd >0) {
            blankContentMessagesCounter += amountToAdd;
        }
    }


    // increment by 1
    public void incrementFieldsErrorsRowsCounter() {
        incrementFieldsErrorsRowsCounter(1);
    }
    // increment by n
    public void incrementFieldsErrorsRowsCounter(int amountToAdd) {
        if (amountToAdd >0) {
            fieldsErrorsRowsCounter += amountToAdd;
        }
    }

    // increment by 1
    public void incrementGroupedCallsOriginCounter(int countryCode) {
        incrementGroupedCallsOriginCounter(countryCode, 1);
    }
    // increment by n
    public void incrementGroupedCallsOriginCounter(int countryCode, int amountToAdd) {
        if (amountToAdd >0) {
            groupedCallsOriginCounter.merge(countryCode, amountToAdd, (v1, v2) -> v1 + v2);
        }
    }

    // increment by 1
    public void incrementGroupedCallsDestinationCounter(int countryCode) {
        incrementGroupedCallsDestinationCounter(countryCode, 1);
    }
    // increment by n
    public void incrementGroupedCallsDestinationCounter(int countryCode, int amountToAdd) {
        if (amountToAdd >0) {
            groupedCallsDestinationCounter.merge(countryCode, amountToAdd, (v1, v2) -> v1 + v2);
        }
    }

    // increment by 1
    public void incrementKoToOkRatio() {
        incrementKoToOkRatio(1);
    }
    // increment by n
    public void incrementKoToOkRatio(int amountToAdd) {
        if (amountToAdd >0) {
            koToOkRatio += amountToAdd;
        }
    }

    // increment by 1
    public void incrementAverageCallDuration() {
        incrementAverageCallDuration(1);
    }
    // increment by n
    public void incrementAverageCallDuration(int amountToAdd) {
        if (amountToAdd >0) {
            averageCallDuration += amountToAdd;
        }
    }


    // getters & setters
    public int getMissingFieldsRowsCounter() {
        return missingFieldsRowsCounter;
    }

    public void setMissingFieldsRowsCounter(int missingFieldsRowsCounter) {
        this.missingFieldsRowsCounter = missingFieldsRowsCounter;
    }

    public int getBlankContentMessagesCounter() {
        return blankContentMessagesCounter;
    }

    public void setBlankContentMessagesCounter(int blankContentMessagesCounter) {
        this.blankContentMessagesCounter = blankContentMessagesCounter;
    }

    public int getFieldsErrorsRowsCounter() {
        return fieldsErrorsRowsCounter;
    }

    public void setFieldsErrorsRowsCounter(int fieldsErrorsRowsCounter) {
        this.fieldsErrorsRowsCounter = fieldsErrorsRowsCounter;
    }

    public Map getGroupedCallsOriginCounter() {
        return groupedCallsOriginCounter;
    }

    public Map getGroupedCallsDestinationCounter() {
        return groupedCallsDestinationCounter;
    }

    public float getKoToOkRatio() {
        return koToOkRatio;
    }

    public void setKoToOkRatio(float koToOkRatio) {
        this.koToOkRatio = koToOkRatio;
    }

    public float getAverageCallDuration() {
        return averageCallDuration;
    }

    public void setAverageCallDuration(float averageCallDuration) {
        this.averageCallDuration = averageCallDuration;
    }

    public String getGivenWordsRanking() {
        return givenWordsRanking;
    }

    public void setGivenWordsRanking(String givenWordsRanking) {
        this.givenWordsRanking = givenWordsRanking;
    }
}
