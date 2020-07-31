package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// this class works as a (map) repository of all country codes together with their country names
@Repository
public class CountryCodeMap {
    private Map countryCodes = new HashMap<String, String>();


    public CountryCodeMap () {
        countryCodes.put("34","Spain");
        countryCodes.put("49","Germany");
        countryCodes.put("44","United Kingdom");
        // there are no completed country codes here
    }

    // put & remove method?

    public String get(String key) {
        return (String) countryCodes.get(key);
    }
}
