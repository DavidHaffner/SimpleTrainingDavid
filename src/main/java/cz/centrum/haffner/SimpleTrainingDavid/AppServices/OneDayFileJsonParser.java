package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OneDayFileJsonParser {

    private int callsCounter = 0;
    private int okCallsCounter = 0;
    private int koCallsCounter = 0;

    @Autowired
    private KpisInfoData kpisInfoData;

    private MetricsInfoData metricsInfoData;


    public MetricsInfoData parseOneDayFile (File inputFile) {
        // new data instance with zero values
        metricsInfoData = new MetricsInfoData();

        // incrementation of parser counters to zero
        incrementCountersToZeroValues();

        //
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String fileLine;
            ObjectMapper objectMapper = new ObjectMapper();

            // row by row data (jsons) processing
            while ((fileLine = br.readLine()) != null) {
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                // converts JSON to Map
                jsonMap = objectMapper.readValue(fileLine, new TypeReference<Map<String, Object>>(){});

                // CALL type of file row
                if ("CALL".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ( "".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("duration")) ||
                            "".equals(jsonMap.get("status_code")) ||
                            "".equals(jsonMap.get("status_description")) )
                        {metricsInfoData.addOneToMissingFieldsRowsCounter();}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("duration").getClass() != Integer.class ||
                            !( "OK".equals(jsonMap.get("status_code")) || "KO".equals(jsonMap.get("status_code")) ) ||
                            jsonMap.get("status_description").getClass() != String.class )
                        {metricsInfoData.addOneToFieldsErrorsRowsCounter();}

                    // relation between OK/KO calls
                    if ( "OK".equals(jsonMap.get("status_code")) ) {okCallsCounter ++;}
                    if ( "KO".equals(jsonMap.get("status_code")) ) {koCallsCounter ++;}

                    // average call duration
                    if ( jsonMap.get("duration").getClass() == Integer.class ) {
                        metricsInfoData.setAverageCallDuration(
                                ( metricsInfoData.getAverageCallDuration() * callsCounter + (int)jsonMap.get("duration") )
                                / ++callsCounter );
                    }

                    kpisInfoData.addOneToTotalCallsNumber();

                // MSG type of file row
                } else if ("MSG".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ("".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("message_status")) )
                        {metricsInfoData.addOneToMissingFieldsRowsCounter();}

                    // messages with blank content
                    if ("".equals(jsonMap.get("message_content")) ) {
                        metricsInfoData.addOneToBlankContentMessagesCounter();}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("message_content").getClass() != String.class ||
                            !( "DELIVERED".equals(jsonMap.get("message_status")) || "SEEN".equals(jsonMap.get("message_status")) ))
                        {metricsInfoData.addOneToFieldsErrorsRowsCounter();}

                    kpisInfoData.addOneToTotalMessagesNumber();

                } else if ("".equals( jsonMap.get("message_type") )){
                    metricsInfoData.addOneToMissingFieldsRowsCounter();
                } else {
                    // field error in message_type
                    metricsInfoData.addOneToFieldsErrorsRowsCounter();
                }

                kpisInfoData.addOneToTotalRowsNumber();
            }
            kpisInfoData.addOneToProcessedFilesNumber();

            // final mapping
            metricsInfoData.setGroupedCallsCounter(callsCounter);  // TODO: temporary solution
            metricsInfoData.setKoToOkRatio(koCallsCounter / (float) okCallsCounter);  // the share of KO result to OK result

        } catch (IOException e) {
            e.printStackTrace();
        }
        return metricsInfoData;
    }

    public void incrementCountersToZeroValues() {
        this.callsCounter = 0;
        this.okCallsCounter = 0;
        this.koCallsCounter = 0;
    }
}
