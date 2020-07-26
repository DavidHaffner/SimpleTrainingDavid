package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoDataSet;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OneDayFileJsonParser {
    // TODO: net approach doesn´t work
    // private static final String FILE_PATH = "https://github.com/DavidHaffner/SimpleTrainingDavid/tree/dev/logs/MCP_20180131.json";
    private static final String FILE_PATH_PREFIX = "C://work/SimpleTrainingDavid/src/main/resources/MCPData/";

    private int callsCounter = 0;
    private int okCallsCounter = 0;
    private int koCallsCounter = 0;


    public OneDayFileJsonParser() {
    }


    public MetricsInfoDataSet parseOneDayFile (long requestedDate, MetricsInfoDataSet metricsInfoDataSet) {
        // set the file path
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";

        //
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
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
                    if ("".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("duration")) ||
                            "".equals(jsonMap.get("status_code")) ||
                            "".equals(jsonMap.get("status_description")) )
                    {metricsInfoDataSet.setMissingFieldsRowsCounter( metricsInfoDataSet.getMissingFieldsRowsCounter() +1);}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("duration").getClass() != Integer.class ||
                            !( "OK".equals(jsonMap.get("status_code")) || "KO".equals(jsonMap.get("status_code")) ) ||
                            jsonMap.get("status_description").getClass() != String.class )
                    {metricsInfoDataSet.setFieldsErrorsRowsCounter(metricsInfoDataSet.getFieldsErrorsRowsCounter() +1);}

                    // relation between OK/KO calls
                    if ( "OK".equals(jsonMap.get("status_code")) ) {okCallsCounter ++;}
                    if ( "KO".equals(jsonMap.get("status_code")) ) {koCallsCounter ++;}

                    // average call duration
                    if ( jsonMap.get("duration").getClass() == Integer.class ) {
                        metricsInfoDataSet.setAverageCallDuration(
                                ( metricsInfoDataSet.getAverageCallDuration() * callsCounter + (int)jsonMap.get("duration") )
                                / ++callsCounter );
                    }

                    // MSG type of file row
                } else if ("MSG".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ("".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("message_status")) )
                    {metricsInfoDataSet.setMissingFieldsRowsCounter(metricsInfoDataSet.getMissingFieldsRowsCounter() +1);}

                    // messages with blank content
                    if ("".equals(jsonMap.get("message_content")) ) {
                        metricsInfoDataSet.setBlankContentMessagesCounter(metricsInfoDataSet.getBlankContentMessagesCounter() +1);}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("message_content").getClass() != String.class ||
                            !( "DELIVERED".equals(jsonMap.get("message_status")) || "SEEN".equals(jsonMap.get("message_status")) ))
                    {metricsInfoDataSet.setFieldsErrorsRowsCounter(metricsInfoDataSet.getFieldsErrorsRowsCounter() +1);}

                } else if ("".equals( jsonMap.get("message_type") )){
                    metricsInfoDataSet.setMissingFieldsRowsCounter(metricsInfoDataSet.getMissingFieldsRowsCounter() +1);
                } else {
                    // field error in message_type
                    metricsInfoDataSet.setFieldsErrorsRowsCounter(metricsInfoDataSet.getFieldsErrorsRowsCounter() +1);
                }
            }

            // final mapping
            metricsInfoDataSet.setGroupedCallsCounter(callsCounter);  // TODO: temporary solution
            metricsInfoDataSet.setKoToOkRatio(koCallsCounter / (float) okCallsCounter);  // the share of KO result to OK result

        } catch (IOException e) {
            e.printStackTrace();
        }
        return metricsInfoDataSet;
    }

    public void incrementToZeroValues() {
        this.callsCounter = 0;
        this.okCallsCounter = 0;
        this.koCallsCounter = 0;
    }
}
