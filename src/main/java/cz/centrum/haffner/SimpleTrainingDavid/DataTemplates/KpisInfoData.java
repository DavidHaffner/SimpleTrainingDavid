package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

// singleton containing global service operations counters
@Component
public class KpisInfoData {

    private int processedFilesNumber =0;           // Total number of processed JSON files
    private int totalRowsNumber =0;                // Total number of rows
    private int totalCallsNumber =0;               // Total number of calls
    private int totalMessagesNumber =0;            // Total number of messages
    private int differentOriginsNumber =0;         // Total number of different origin country codes
    private int differentDestinationsNumber =0;    // Total number of different destination country codes
    private List jsonProcessingDurationList = new LinkedList<Long>();     // Duration of each JSON process


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

    public List getLastTenProcessingDurations() {
        int listSize = jsonProcessingDurationList.size();
        if ( listSize <11 ) {
            return jsonProcessingDurationList;
        } else {
            // last 10 elements
            List lastTenElements = new LinkedList();
            for (int i=1; i<11 ; i++) {
               lastTenElements.add( jsonProcessingDurationList.get(listSize-i) );
            }

            return lastTenElements;
        }
    }

    public void addJsonProcessingDuration(long processingDuration) {
        this.jsonProcessingDurationList.add(processingDuration);
    }

}
