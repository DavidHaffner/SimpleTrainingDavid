package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// singleton containing global service operations counters
@Component
public class KpisInfoData {

    private int processedFilesNumber =0;           // Total number of processed JSON files
    private int totalRowsNumber =0;                // Total number of rows
    private int totalCallsNumber =0;               // Total number of calls
    private int totalMessagesNumber =0;            // Total number of messages
    private Set<Integer> differentOriginCodesSet = new HashSet<>();  // Total number of different origin country codes
    private Set<Integer> differentDestinationCodesSet = new HashSet<>(); // Total number of different destination country codes
    private List<Long> jsonProcessingDurationList = new LinkedList<>();  // Duration of each JSON process


    // increment by 1
    public void incrementProcessedFilesNumber() {
        incrementProcessedFilesNumber(1);
    }
    // increment by n
    public void incrementProcessedFilesNumber(int amountToAdd) {
        if (amountToAdd >0) {
            processedFilesNumber += amountToAdd;
        }
    }

    // increment by 1
    public void incrementTotalRowsNumber() {
        incrementTotalRowsNumber(1);
    }
    // increment by n
    public void incrementTotalRowsNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalRowsNumber += amountToAdd;
        }
    }

    // increment by 1
    public void incrementTotalCallsNumber() {
        incrementTotalCallsNumber(1);
    }
    // increment by n
    public void incrementTotalCallsNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalCallsNumber += amountToAdd;
        }
    }

    // increment by 1
    public void incrementTotalMessagesNumber() {
        incrementTotalMessagesNumber(1);
    }
    // increment by n
    public void incrementTotalMessagesNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalMessagesNumber += amountToAdd;
        }
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

    public void addDifferentOriginCodesSet (int originCode) {
        this.differentOriginCodesSet.add(originCode);
    }

    public int getDifferentOriginCodesNumber () {
        return this.differentOriginCodesSet.size();
    }

    public void addDifferentDestinationCodesSet (int destinationCode) {
        this.differentDestinationCodesSet.add(destinationCode);
    }

    public int getDifferentDestinationCodesNumber () {
        return this.differentDestinationCodesSet.size();
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
