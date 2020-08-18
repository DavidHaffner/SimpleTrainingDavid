package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MetricsInfoData {

    private AtomicInteger missingFieldsRowsCounter = new AtomicInteger(0);        // Number of rows with missing fields
    private AtomicInteger blankContentMessagesCounter = new AtomicInteger(0);     // Number of messages with blank content
    private AtomicInteger fieldsErrorsRowsCounter = new AtomicInteger(0);        // Number of rows with fields errors
    private Map<Integer, Integer> groupedCallsOriginCounter = Collections.synchronizedMap(new HashMap());  // Number of calls origin grouped by country code
    private Map<Integer, Integer> groupedCallsDestinationCounter = Collections.synchronizedMap(new HashMap());  // Number of calls destination grouped by country code
    private AtomicReference<Float> koToOkRatio = new AtomicReference<>(0.0f);               // Relationship between OK/KO calls
    private Map<Integer, Float> groupedAverageCallDuration = Collections.synchronizedMap(new HashMap());   // Average call duration grouped by country code
    private Map<String, Integer> givenWordsRanking = Collections.synchronizedMap(new HashMap());   // Word occurrence ranking for the given words in message

    // increment by 1
    public void incrementMissingFieldsRowsCounter() {
        incrementMissingFieldsRowsCounter(1);
    }
    // increment by n
    public void incrementMissingFieldsRowsCounter(int amountToAdd) {
        if (amountToAdd >0) {
            missingFieldsRowsCounter.addAndGet(amountToAdd);
        }
    }

    // increment by 1
    public void incrementBlankContentMessagesCounter() {
        incrementBlankContentMessagesCounter(1);
    }
    // increment by n
    public void incrementBlankContentMessagesCounter(int amountToAdd) {
        if (amountToAdd >0) {
            blankContentMessagesCounter.addAndGet(amountToAdd);
        }
    }


    // increment by 1
    public void incrementFieldsErrorsRowsCounter() {
        incrementFieldsErrorsRowsCounter(1);
    }
    // increment by n
    public void incrementFieldsErrorsRowsCounter(int amountToAdd) {
        if (amountToAdd >0) {
            fieldsErrorsRowsCounter.addAndGet(amountToAdd);
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
    public void incrementGivenWordsRanking(String word) {
        incrementGivenWordsRanking(word, 1);
    }
    // increment by n
    public void incrementGivenWordsRanking(String word, int amountToAdd) {
        if (amountToAdd >0) {
            givenWordsRanking.merge(word, amountToAdd, (v1, v2) -> v1 + v2);
        }
    }

    // getters & setters
    public int getMissingFieldsRowsCounter() {
        return missingFieldsRowsCounter.get();
    }

    public void setMissingFieldsRowsCounter(int missingFieldsRowsCounter) {
        this.missingFieldsRowsCounter.set(missingFieldsRowsCounter);
    }

    public int getBlankContentMessagesCounter() {
        return blankContentMessagesCounter.get();
    }

    public void setBlankContentMessagesCounter(int blankContentMessagesCounter) {
        this.blankContentMessagesCounter.set(blankContentMessagesCounter);
    }

    public int getFieldsErrorsRowsCounter() {
        return fieldsErrorsRowsCounter.get();
    }

    public void setFieldsErrorsRowsCounter(int fieldsErrorsRowsCounter) {
        this.fieldsErrorsRowsCounter.set(fieldsErrorsRowsCounter);
    }

    public Map getGroupedCallsOriginCounter() {
        return groupedCallsOriginCounter;
    }

    public Map getGroupedCallsDestinationCounter() {
        return groupedCallsDestinationCounter;
    }

    public float getKoToOkRatio() {
        return koToOkRatio.get();
    }

    public void setKoToOkRatio(float koToOkRatio) {
        this.koToOkRatio.set(koToOkRatio);
    }

    public Map<Integer, Float> getGroupedAverageCallDuration() {
        return groupedAverageCallDuration;
    }

    public float getAverageCallDurationOfCC(int countryCode) {
        return ( groupedAverageCallDuration.get(countryCode) == null ?
                0.0f : groupedAverageCallDuration.get(countryCode) );
    }

    public void setAverageCallDurationOfCC(int countryCode, float newAverageCallDuration) {
        groupedAverageCallDuration.put(countryCode, newAverageCallDuration);
    }

    public Map<String, Integer> getGivenWordsRanking() {
        return givenWordsRanking;
    }

    @Override
    public String toString() {
        return "MetricsInfoData{" +
                "missingFieldsRowsCounter=" + missingFieldsRowsCounter +
                ", blankContentMessagesCounter=" + blankContentMessagesCounter +
                ", fieldsErrorsRowsCounter=" + fieldsErrorsRowsCounter +
                ", groupedCallsOriginCounter=" + groupedCallsOriginCounter +
                ", groupedCallsDestinationCounter=" + groupedCallsDestinationCounter +
                ", koToOkRatio=" + koToOkRatio +
                ", groupedAverageCallDuration=" + groupedAverageCallDuration +
                ", givenWordsRanking=" + givenWordsRanking +
                '}';
    }
}
