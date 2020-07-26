package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

@Component
public class MetricsInfoDataSet {

    private int missingFieldsRowsCounter =0;        // Number of rows with missing fields
    private int blankContentMessagesCounter =0;     // Number of messages with blank content
    private int fieldsErrorsRowsCounter =0;        // Number of rows with fields errors
    private int groupedCallsCounter =0;            // Number of calls TODO: origin/destination grouped by country code
    private float koToOkRatio =0.0f;               // Relationship between OK/KO calls
    private float averageCallDuration =0.0f;            // Average call duration TODO: grouped by country code
    private String givenWordsRanking ="UNDER CONSTRUCTION YET";  // TODO: Word occurrence ranking for the given words in message


    public MetricsInfoDataSet() {
    }


    public void incrementToZeroValues() {
        this.missingFieldsRowsCounter =0;
        this.blankContentMessagesCounter =0;
        this.fieldsErrorsRowsCounter =0;
        this.groupedCallsCounter =0;
        this.koToOkRatio =0.0f;
        this.averageCallDuration =0.0f;
        this.givenWordsRanking ="UNDER CONSTRUCTION YET";
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

    public int getGroupedCallsCounter() {
        return groupedCallsCounter;
    }

    public void setGroupedCallsCounter(int groupedCallsCounter) {
        this.groupedCallsCounter = groupedCallsCounter;
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
