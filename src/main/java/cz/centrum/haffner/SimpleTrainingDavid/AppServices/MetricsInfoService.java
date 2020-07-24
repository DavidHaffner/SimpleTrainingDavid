package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// algorithm for returning of the proper MetricsInfo set
public class MetricsInfoService {

    // TODO: net approach doesn´t work
    // private static final String FILE_PATH = "https://github.com/DavidHaffner/SimpleTrainingDavid/tree/dev/logs/MCP_20180131.json";
    private static final String FILE_PATH_PREFIX = "C://work/SimpleTrainingDavid/src/main/resources/MCPData/";

    private int missingFieldsRowsCounter =0;        // Number of rows with missing fields
    private int blankContentMessagesCounter =0;     // Number of messages with blank content
    private int fieldsErrorsRowsCounter =0;        // Number of rows with fields errors
    private int groupedCallsCounter =0;            // Number of calls TODO: origin/destination grouped by country code
    private float koToOkRatio =0.0f;               // Relationship between OK/KO calls
    private float averageCallDuration =0.0f;            // Average call duration TODO: grouped by country code
    private String givenWordsRanking ="UNDER CONSTRUCTION YET";  // TODO: Word occurrence ranking for the given words in message_content field


    public MetricsInfoService(){
    }

    public void proceedMetricsInfoSet(long requestedDate) {
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String fileLine;
            ObjectMapper objectMapper = new ObjectMapper();

            // file variables
            int callsCounter = 0;
            int okCallsCounter = 0;
            int koCallsCounter = 0;

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
                        {missingFieldsRowsCounter ++;}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("duration").getClass() != Integer.class ||
                            !( "OK".equals(jsonMap.get("status_code")) || "KO".equals(jsonMap.get("status_code")) ) ||
                            jsonMap.get("status_description").getClass() != String.class )
                    {fieldsErrorsRowsCounter ++;}

                    // relation between OK/KO calls
                    if ( "OK".equals(jsonMap.get("status_code")) ) {okCallsCounter ++;}
                    if ( "KO".equals(jsonMap.get("status_code")) ) {koCallsCounter ++;}

                    // average call duration
                    if ( jsonMap.get("duration").getClass() == Integer.class ) {
                        averageCallDuration = ( averageCallDuration * callsCounter
                                                + (int)jsonMap.get("duration") )
                                                / ++callsCounter;
                    }

                // MSG type of file row
                } else if ("MSG".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ("".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("message_status")) )
                        {missingFieldsRowsCounter ++;}

                    // messages with blank content
                    if ("".equals(jsonMap.get("message_content")) ) {blankContentMessagesCounter ++;}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            jsonMap.get("destination").getClass() != Long.class ||
                            jsonMap.get("message_content").getClass() != String.class ||
                            !( "DELIVERED".equals(jsonMap.get("message_status")) || "SEEN".equals(jsonMap.get("message_status")) ))
                    {fieldsErrorsRowsCounter ++;}



                } else if ("".equals( jsonMap.get("message_type") )){
                    missingFieldsRowsCounter ++;
                } else {
                    // field error in message_type
                    fieldsErrorsRowsCounter ++;
                }
            }

            // final mapping
            groupedCallsCounter = callsCounter;  // TODO: temporary solution
            koToOkRatio = koCallsCounter / (float) okCallsCounter;  // share of KO result to OK result

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMissingFieldsRowsCounter() {
        return missingFieldsRowsCounter;
    }

    public void setMissingFieldsRowsCounter(int missingFieldsRowsCounter) {
        this.missingFieldsRowsCounter = missingFieldsRowsCounter;
    }

    public int getBlankContentMessagesCounter() {
        return blankContentMessagesCounter;
    }

    public void setBlankContentMessagesCounter(int blankContentMessagesCounter) {
        this.blankContentMessagesCounter = blankContentMessagesCounter;
    }

    public int getFieldsErrorsRowsCounter() {
        return fieldsErrorsRowsCounter;
    }

    public void setFieldsErrorsRowsCounter(int fieldsErrorsRowsCounter) {
        this.fieldsErrorsRowsCounter = fieldsErrorsRowsCounter;
    }

    public int getGroupedCallsCounter() {
        return groupedCallsCounter;
    }

    public void setGroupedCallsCounter(int groupedCallsCounter) {
        this.groupedCallsCounter = groupedCallsCounter;
    }

    public float getKoToOkRatio() {
        return koToOkRatio;
    }

    public void setKoToOkRatio(float koToOkRatio) {
        this.koToOkRatio = koToOkRatio;
    }

    public float getAverageCallDuration() {
        return averageCallDuration;
    }

    public void setAverageCallDuration(float averageCallDuration) {
        this.averageCallDuration = averageCallDuration;
    }

    public String getGivenWordsRanking() {
        return givenWordsRanking;
    }

    public void setGivenWordsRanking(String givenWordsRanking) {
        this.givenWordsRanking = givenWordsRanking;
    }
}
