package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO (delete): use this component, or rather delegate this process to a method in OneDayFileJsonParser) ?
// this class is processing the split of sms text into word ranking map
@Component
public class GivenWordsMonitor implements Monitor {

    public void process(String smsText) {
        for (String particularWord : smsText.split(" ") ) {
            // set particular words into givenWordsRanking map
        }
    }
}
