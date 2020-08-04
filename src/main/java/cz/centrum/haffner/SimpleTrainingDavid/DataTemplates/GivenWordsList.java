package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

// this class works as the repository of key words monitored in SMS messages
@Repository
public class GivenWordsList {
    private List<String> givenWords = new LinkedList<>();


    public GivenWordsList () {
        givenWords.add("HELLO");
    }

    public List<String> getGivenWords() {
        return givenWords;
    }
}
