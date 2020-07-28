package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class KpisInfoProcessor {
    private Set differentOriginsSet = new HashSet<Integer>();
    private Set differentDestinationsSet = new HashSet<Integer>();

    @Autowired
    private KpisInfoData kpisInfoData;


    public void addOneToProcessedFilesNumber() { kpisInfoData.addOneToProcessedFilesNumber(); }

    public void addOneToTotalRowsNumber() { kpisInfoData.addOneToTotalRowsNumber(); }

    public void addOneToTotalCallsNumber() {
        kpisInfoData.addOneToTotalCallsNumber();
    }

    public void addOneToTotalMessagesNumber() {
        kpisInfoData.addOneToTotalMessagesNumber();
    }

    public void addOriginsCode(int originsCode) {
        differentOriginsSet.add(originsCode);
        kpisInfoData.setDifferentOriginsNumber( differentOriginsSet.size() );
    }

    public void addDestinationsCode(int destinationsCode) {
        differentDestinationsSet.add(destinationsCode);
        kpisInfoData.setDifferentDestinationsNumber( differentDestinationsSet.size() );
    }

    public void processAverageDuration() {    // TODO
        // algorithm of averiging;
    }


}
