package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// algorithm for returning of the proper MetricsInfo data set
@Service
public class MetricsInfoService {
    @Autowired
    private MetricsInfoDataSet metricsInfoDataSet;

    @Autowired
    private OneDayFileJsonParser oneDayFileJsonsParser;


    public MetricsInfoService(){
    }


    public MetricsInfoDataSet proceedMetricsInfoDataSet(long requestedDate) {
        // incrementation of dataset & parser counters to zero
        metricsInfoDataSet.incrementToZeroValues();
        oneDayFileJsonsParser.incrementToZeroValues();

        // parsing of one day file data to the final metrics info data set + return
        return oneDayFileJsonsParser.parseOneDayFile(requestedDate, metricsInfoDataSet);
    }
}
