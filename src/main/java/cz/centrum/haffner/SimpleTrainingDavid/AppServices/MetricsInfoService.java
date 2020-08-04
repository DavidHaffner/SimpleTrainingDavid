package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

// algorithm for returning of the proper MetricsInfo data
@Service
public class MetricsInfoService {
    private static final String FILE_PATH_PREFIX = "C:/Users/David Haffner/IdeaProjects/SimpleTrainingDavid/target/classes/MCPData/";
    // private static final String FILE_PATH_PREFIX = MetricsInfoService.class.getResource("/MCPData/").getPath();
    @Autowired
    private OneDayFileJsonParser oneDayFileJsonsParser;


    public MetricsInfoData processMetricsInfoData(long requestedDate) {
        // get the file of requested date
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";
        File oneDayFile = new File(filePath);

        return oneDayFileJsonsParser.process(oneDayFile);
    }
}