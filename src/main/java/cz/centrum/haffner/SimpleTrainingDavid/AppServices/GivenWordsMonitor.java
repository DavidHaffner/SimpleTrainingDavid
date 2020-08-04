package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.GivenWordsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// this class is monitoring, whether given SMS text contents words from the list
@Component
public class GivenWordsMonitor {
    @Autowired
    GivenWordsList givenWordsList;

    public boolean process(String smsText) {
        for ( String keyWord : givenWordsList.getGivenWords() ) {
            if ( smsText.contains(keyWord) ) { return true;}
        }
        return false;

        // givenWordsList.getGivenWords().stream().forEach( word -> smsText.contains(word) );
    }
}
