package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// singleton containing global service operations counters
@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "processedFilesNumber",
        "totalRowsNumber",
        "totalCallsNumber",
        "totalMessagesNumber",
        "differentOriginCodesSet",
        "differentDestinationCodesSet",
        "jsonProcessingDurationList"
})
@XmlRootElement(name = "getKpisResponse")
public class KpisInfoData {

    private AtomicInteger processedFilesNumber = new AtomicInteger(0); // Total number of processed JSON files
    private AtomicInteger totalRowsNumber = new AtomicInteger(0);      // Total number of rows
    private AtomicInteger totalCallsNumber = new AtomicInteger(0);     // Total number of calls
    private AtomicInteger totalMessagesNumber = new AtomicInteger(0);  // Total number of messages
    @XmlElementWrapper(name="differentOriginCodesSet")
    @XmlElement(name="originCode")
    private Set<Integer> differentOriginCodesSet = Collections.synchronizedSet(new HashSet<>());  // Total number of different origin country codes
    @XmlElementWrapper(name="differentDestinationCodesSet")
    @XmlElement(name="destinationCode")
    private Set<Integer> differentDestinationCodesSet = Collections.synchronizedSet(new HashSet<>()); // Total number of different destination country codes
    @XmlTransient  // this field is transformed into xml by using of its getter (returns 10 last values only)
    private List<Long> jsonProcessingDurationList = Collections.synchronizedList(new LinkedList<>());  // Duration of each JSON process


    // increment by 1
    public void incrementProcessedFilesNumber() {
        incrementProcessedFilesNumber(1);
    }
    // increment by n
    public void incrementProcessedFilesNumber(int amountToAdd) {
        if (amountToAdd >0) {
            processedFilesNumber.addAndGet(amountToAdd);
        }
    }

    // increment by 1
    public void incrementTotalRowsNumber() {
        incrementTotalRowsNumber(1);
    }
    // increment by n
    public void incrementTotalRowsNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalRowsNumber.addAndGet(amountToAdd);
        }
    }

    // increment by 1
    public void incrementTotalCallsNumber() {
        incrementTotalCallsNumber(1);
    }
    // increment by n
    public void incrementTotalCallsNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalCallsNumber.addAndGet(amountToAdd);
        }
    }

    // increment by 1
    public void incrementTotalMessagesNumber() {
        incrementTotalMessagesNumber(1);
    }
    // increment by n
    public void incrementTotalMessagesNumber(int amountToAdd) {
        if (amountToAdd >0) {
            totalMessagesNumber.addAndGet(amountToAdd);
        }
    }



    // getters & setters
    public int getProcessedFilesNumber() {
        return processedFilesNumber.get();
    }

    public void setProcessedFilesNumber(int processedFilesNumber) {
        this.processedFilesNumber.set(processedFilesNumber);
    }

    public int getTotalRowsNumber() {
        return totalRowsNumber.get();
    }

    public void setTotalRowsNumber(int totalRowsNumber) {
        this.totalRowsNumber.set(totalRowsNumber);
    }

    public int getTotalCallsNumber() {
        return totalCallsNumber.get();
    }

    public void setTotalCallsNumber(int totalCallsNumber) {
        this.totalCallsNumber.set(totalCallsNumber);
    }

    public int getTotalMessagesNumber() {
        return totalMessagesNumber.get();
    }

    public void setTotalMessagesNumber(int totalMessagesNumber) {
        this.totalMessagesNumber.set(totalMessagesNumber);
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

    @XmlElementWrapper(name="jsonProcessingDurationList")
    @XmlElement(name="duration")
    public List getJsonProcessingDurationList() {
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
