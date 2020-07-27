package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

// algorithm for returning of the proper MetricsInfo data
@Service
public class MetricsInfoService {
    // TODO: net approach doesn´t work
    // private static final String FILE_PATH = "https://github.com/DavidHaffner/SimpleTrainingDavid/tree/dev/logs/MCP_20180131.json";
    private static final String FILE_PATH_PREFIX = "C://work/SimpleTrainingDavid/src/main/resources/MCPData/";

    @Autowired
    private OneDayFileJsonParser oneDayFileJsonsParser;


    public MetricsInfoData processMetricsInfoData(long requestedDate) {
        // get the file of requested date
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";
        File oneDayFile = new File(filePath);

        return oneDayFileJsonsParser.parseOneDayFile(oneDayFile);
    }
}
