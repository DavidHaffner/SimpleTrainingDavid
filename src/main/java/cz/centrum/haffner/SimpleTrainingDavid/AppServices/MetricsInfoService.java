package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

// algorithm for returning of the proper MetricsInfo data
@Service
public class MetricsInfoService {
    private static final Logger logger = LogManager.getLogger(MetricsInfoService.class);

    private static final String FILE_PATH_PREFIX = "https://raw.githubusercontent.com/TomasStesti/simpleTraining/master/logs/";
    //private static final String FILE_PATH_PREFIX = MetricsInfoService.class.getResource("/MCPData/").getPath();
    @Autowired
    private Parser oneDayFileJsonsParser;


    public MetricsInfoData processData(long requestedDate) {
        if(logger.isDebugEnabled()) {
            logger.debug("Starting to process metrics data of date: " + requestedDate);
        }

        // get the file of requested date
        String filePath = FILE_PATH_PREFIX + "MCP_" + requestedDate + ".json";

        URL oneDayFile = null;
        try {
            oneDayFile = new URL(filePath);
            if(logger.isDebugEnabled()) {
                logger.debug("Successfully received file from: " + filePath);
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }

        return oneDayFileJsonsParser.parse(oneDayFile);
    }
}