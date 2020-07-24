package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoSet;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.OneMcpRecordSet;

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
    private static final String FILE_PATH = "C://work/SimpleTrainingDavid/src/main/resources/MCPData/MCP_20180131.json";


    public MetricsInfoService() {

    }

    public MetricsInfoSet proceedMetricsInfoSet() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(FILE_PATH)))) {
            String fileLine;
            ObjectMapper objectMapper = new ObjectMapper();

            while ((fileLine = br.readLine()) != null) {
                // process the line
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                // converts JSON to Map
                jsonMap = objectMapper.readValue(fileLine, new TypeReference<Map<String, Object>>(){});
                System.out.println(jsonMap);

                /*
                if ("CALL".equals( oneRowRecordSet.getMessageType() )) {
                    System.out.println(oneRowRecordSet.getMessageType());
                } else if ("MSG".equals( oneRowRecordSet.getMessageType() )) {
                    System.out.println(oneRowRecordSet.getMessageType());
                } else {
                    // TODO: mistake -> exception
                    System.out.println("chyba v message_type...");
                } */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        return new MetricsInfoSet (1,2,3,4,5,6,7);
    }
}
