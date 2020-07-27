package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

// singleton containing global service operations counters
@Component
public class KpisInfoData {

    private int processedFilesNumber =0;           // Total number of processed JSON files
    private int totalRowsNumber =0;                // Total number of rows
    private int totalCallsNumber =0;               // Total number of calls
    private int totalMessagesNumber =0;            // Total number of messages
    private int differentOriginsNumber =0;         // Total number of different origin country codes
    private int differentDestinationsNumber =0;    // Total number of different destination country codes
    private int averageJsonProcessDuration =0;     // Duration of each JSON process (ms)


    public void addOneToProcessedFilesNumber() {
        this.processedFilesNumber ++;
    }

    public void addOneToTotalRowsNumber() {
        this.totalRowsNumber ++;
    }

    public void addOneToTotalCallsNumber() {
        this.totalCallsNumber ++;
    }

    public void addOneToTotalMessagesNumber() {
        this.totalMessagesNumber ++;
    }

    public void addOneToDifferentOriginsNumber() {
        this.differentOriginsNumber ++;
    }

    public void addOneToDifferentDestinationsNumber() {
        this.differentDestinationsNumber ++;
    }

    public void processAverageDuration() {
        // algorithm of averiging;
    }


    // getters & setters
    public int getProcessedFilesNumber() {
        return processedFilesNumber;
    }

    public void setProcessedFilesNumber(int processedFilesNumber) {
        this.processedFilesNumber = processedFilesNumber;
    }

    public int getTotalRowsNumber() {
        return totalRowsNumber;
    }

    public void setTotalRowsNumber(int totalRowsNumber) {
        this.totalRowsNumber = totalRowsNumber;
    }

    public int getTotalCallsNumber() {
        return totalCallsNumber;
    }

    public void setTotalCallsNumber(int totalCallsNumber) {
        this.totalCallsNumber = totalCallsNumber;
    }

    public int getTotalMessagesNumber() {
        return totalMessagesNumber;
    }

    public void setTotalMessagesNumber(int totalMessagesNumber) {
        this.totalMessagesNumber = totalMessagesNumber;
    }

    public int getDifferentOriginsNumber() {
        return differentOriginsNumber;
    }

    public void setDifferentOriginsNumber(int differentOriginsNumber) {
        this.differentOriginsNumber = differentOriginsNumber;
    }

    public int getDifferentDestinationsNumber() {
        return differentDestinationsNumber;
    }

    public void setDifferentDestinationsNumber(int differentDestinationsNumber) {
        this.differentDestinationsNumber = differentDestinationsNumber;
    }

    public int getAverageJsonProcessDuration() {
        return averageJsonProcessDuration;
    }

    public void setAverageJsonProcessDuration(int averageJsonProcessDuration) {
        this.averageJsonProcessDuration = averageJsonProcessDuration;
    }
}
