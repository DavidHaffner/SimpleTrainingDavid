package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
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
public class SimpleTrainingDavidRestController {
    private static final Logger logger = LogManager.getLogger(SimpleTrainingDavidRestController.class);

    @Autowired
    private MetricsInfoService metricsInfoService;
    @Autowired
    private KpisInfoService kpisInfoService;
    @Autowired
    KafkaSimpleProducer kafkaSimpleProducer;

    @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
    public @ResponseBody ResponseEntity<Object> getMetrics(@PathVariable long requestedDate) {
        logger.debug("Receiving metrics REST request from date: {}", requestedDate);

        try {
            MetricsInfoData response = metricsInfoService.processData(requestedDate);

            // producing metrics data as json into Kafka
            kafkaSimpleProducer.produceToTopic("myTopic", response);
            logger.debug("Returning metrics REST response.");
            return new ResponseEntity<>( response, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( "ERROR WHEN OBTAINING DATA FROM FILE:  " + e.getMessage(), HttpStatus.NOT_FOUND );
        }
    }

    @GetMapping("/kpis")
    public @ResponseBody ResponseEntity<KpisInfoData> getKpis() {
        logger.debug("Receiving kpis REST request.");

        KpisInfoData response = kpisInfoService.processData();

        logger.debug("Returning kpis REST response.");
        return new ResponseEntity<KpisInfoData>(response, HttpStatus.OK);
    }
}
