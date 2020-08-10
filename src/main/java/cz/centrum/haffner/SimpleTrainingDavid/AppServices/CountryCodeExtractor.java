package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.CountryCodeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryCodeExtractor implements Extractor {
    @Autowired
    private CountryCodeMap countryCodeMap;

    // extracts country code from MSISDN
    public int extract(Object msisdn) {

        if ( "".equals(msisdn) ) {
            return -1;   // the code for missing value
        } else {
            String msisdnString = String.valueOf(msisdn);

            if (countryCodeMap.get(msisdnString.substring(0)) != null) {
                return Integer.parseInt(msisdnString.substring(0));
            } else if (countryCodeMap.get(msisdnString.substring(0, 2)) != null) {
                return Integer.parseInt(msisdnString.substring(0, 2));
            } else if (countryCodeMap.get(msisdnString.substring(0, 3)) != null) {
                return Integer.parseInt(msisdnString.substring(0, 3));
            } else {
                return 0;     // the code of an invalid value (CC not found in the CC map)
            }
        }
    }
}
