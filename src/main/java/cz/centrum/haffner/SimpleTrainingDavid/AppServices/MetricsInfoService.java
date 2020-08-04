package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

// algorithm for returning of the proper MetricsInfo data
@Service
public class MetricsInfoService {
    private static final String FILE_PATH_PREFIX = "https://raw.githubusercontent.com/TomasStesti/simpleTraining/master/logs/";
    //private static final String FILE_PATH_PREFIX = MetricsInfoService.class.getResource("/MCPData/").getPath();
    @Autowired
    private Parser oneDayFileJsonsParser;


    public MetricsInfoData processData(long requestedDate) {
        // get the file of requested date
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";

        URL oneDayFile = null;
        try {
            oneDayFile = new URL(filePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return oneDayFileJsonsParser.parse(oneDayFile);
    }
}