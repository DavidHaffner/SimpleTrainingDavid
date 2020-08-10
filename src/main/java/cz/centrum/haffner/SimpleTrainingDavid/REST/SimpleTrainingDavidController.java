package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.OneDayFileJsonParser;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTrainingDavidController {
    private static final Logger logger = LogManager.getLogger(SimpleTrainingDavidController.class);

    @Autowired
    private MetricsInfoService metricsInfoService;
    @Autowired
    private KpisInfoService kpisInfoService;


    @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
    public @ResponseBody ResponseEntity<MetricsInfoData> getMetrics(@PathVariable long requestedDate) {
        if(logger.isDebugEnabled()) {
            logger.debug("Receiving /metrics request from date: " + requestedDate);
        }

        MetricsInfoData response = metricsInfoService.processData(requestedDate);

        if(logger.isDebugEnabled()) {
            logger.debug("Returning /metrics response.");
        }
        return new ResponseEntity<MetricsInfoData>(response, HttpStatus.OK);
    }


    @GetMapping("/kpis")
    public @ResponseBody ResponseEntity<KpisInfoData> getKpis() {
        if(logger.isDebugEnabled()) {
            logger.debug("Receiving /kpis request.");
        }

        KpisInfoData response = kpisInfoService.processData();

        if(logger.isDebugEnabled()) {
            logger.debug("Returning /kpis response.");
        }
        return new ResponseEntity<KpisInfoData>(response, HttpStatus.OK);
    }
}
