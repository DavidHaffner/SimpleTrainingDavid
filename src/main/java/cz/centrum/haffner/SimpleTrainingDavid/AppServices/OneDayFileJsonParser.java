package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleConsumer;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class OneDayFileJsonParser implements Parser {
    private static final Logger logger = LogManager.getLogger(OneDayFileJsonParser.class);

    @Autowired
    private KpisInfoData kpisInfoData;
    @Autowired
    private Extractor countryCodeExtractor;
    @Autowired
    private KafkaSimpleProducer kafkaSimpleProducer;
    @Autowired
    private KafkaSimpleConsumer kafkaSimpleConsumer;

    private MetricsInfoData metricsInfoData;


    public MetricsInfoData parse(URL inputUrl) {
        if(logger.isDebugEnabled()) {
            logger.debug("Starting to parse.");
        }

        // new data instance with zero values
        metricsInfoData = new MetricsInfoData();

        // local one file counters
        int rowsCounter = 0;
        int callsCounter = 0;
        int okCallsCounter = 0;
        int koCallsCounter = 0;

        // main process
        try ( BufferedReader br = new BufferedReader( new InputStreamReader(inputUrl.openStream()) ) ) {
            String fileLine;
            ObjectMapper objectMapper = new ObjectMapper();

            if(logger.isDebugEnabled()) {
                logger.debug("Starting of one day file processing.");
            }

            // row by row data (jsons) processing
            while ((fileLine = br.readLine()) != null) {
                Instant startingJsonProcess = Instant.now();

                Map<String, Object> jsonMap = new HashMap<String, Object>();
                // converts JSON to Map
                jsonMap = objectMapper.readValue(fileLine, new TypeReference<Map<String, Object>>(){});

                // get Origins & Destinations country code
                int originCountryCode = countryCodeExtractor.extract( jsonMap.get("origin") );
                    // ((Number)jsonMap.get("origin")).longValue()
                int destinationCountryCode = countryCodeExtractor.extract( jsonMap.get("destination") );

                // CALL type of file row
                if ("CALL".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ( "".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("duration")) ||
                            "".equals(jsonMap.get("status_code")) ||
                            "".equals(jsonMap.get("status_description")) )
                        {metricsInfoData.incrementMissingFieldsRowsCounter();}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            originCountryCode == 0 ||                                   // 0 means CC not found
                            jsonMap.get("destination").getClass() != Long.class ||
                            destinationCountryCode == 0 ||                              // 0 means CC not found
                            jsonMap.get("duration").getClass() != Integer.class ||
                            !( "OK".equals(jsonMap.get("status_code")) || "KO".equals(jsonMap.get("status_code")) ) ||
                            jsonMap.get("status_description").getClass() != String.class )
                        {metricsInfoData.incrementFieldsErrorsRowsCounter();}

                    // number of calls origin/destination grouped by country code
                    if (originCountryCode >0) {
                        metricsInfoData.incrementGroupedCallsOriginCounter(originCountryCode);
                    }
                    if (destinationCountryCode >0) {
                        metricsInfoData.incrementGroupedCallsDestinationCounter(destinationCountryCode);
                    }

                    // relation between OK/KO calls
                    if ( "OK".equals(jsonMap.get("status_code")) ) {okCallsCounter ++;}
                    if ( "KO".equals(jsonMap.get("status_code")) ) {koCallsCounter ++;}

                    // average call duration grouped by country code
                    if ( jsonMap.get("duration").getClass() == Integer.class ) {
                        metricsInfoData.setAverageCallDurationOfCC(originCountryCode,
                                (metricsInfoData.getAverageCallDurationOfCC(originCountryCode)
                                        * callsCounter + (int)jsonMap.get("duration") )
                                        / ++callsCounter );
                    }

                    kpisInfoData.incrementTotalCallsNumber();

                // MSG type of file row
                } else if ("MSG".equals( jsonMap.get("message_type") )) {

                    // rows with missing fields
                    if ("".equals(jsonMap.get("timestamp")) ||
                            "".equals(jsonMap.get("origin")) ||
                            "".equals(jsonMap.get("destination")) ||
                            "".equals(jsonMap.get("message_status")) )
                        {metricsInfoData.incrementMissingFieldsRowsCounter();}

                    // messages with blank content
                    if ("".equals(jsonMap.get("message_content")) ) {
                        metricsInfoData.incrementBlankContentMessagesCounter();}

                    // rows with field errors
                    if ( jsonMap.get("timestamp").getClass() != Long.class ||
                            jsonMap.get("origin").getClass() != Long.class ||
                            originCountryCode == 0 ||                                   // 0 means CC not found
                            jsonMap.get("destination").getClass() != Long.class ||
                            destinationCountryCode == 0 ||                              // 0 means CC not found
                            jsonMap.get("message_content").getClass() != String.class ||
                            !( "DELIVERED".equals(jsonMap.get("message_status")) || "SEEN".equals(jsonMap.get("message_status")) ))
                        {metricsInfoData.incrementFieldsErrorsRowsCounter();}

                    // Word occurrence ranking for the given words in message_content field
                    if ( jsonMap.get("message_content").getClass() == String.class &&
                            !( "".equals( jsonMap.get("message_content") )) ) {
                        processWordOccurrenceRanking( (String)jsonMap.get("message_content") );
                    }

                    kpisInfoData.incrementTotalMessagesNumber();

                } else if ("".equals( jsonMap.get("message_type") )){
                    metricsInfoData.incrementMissingFieldsRowsCounter();
                } else {
                    // field error in message_type
                    metricsInfoData.incrementFieldsErrorsRowsCounter();
                }

                kpisInfoData.incrementTotalRowsNumber();
                if (originCountryCode >0) {                                 // value of -1 or 0 means invalid code
                    kpisInfoData.addDifferentOriginCodesSet(originCountryCode);}
                if (destinationCountryCode >0) {                            // value of -1 or 0 means invalid code
                    kpisInfoData.addDifferentDestinationCodesSet(destinationCountryCode);}

                // sleeping block for higher visibility of particular processes duration
                try {
                    Thread.sleep(50);
                } catch(InterruptedException ex){
                    logger.error(ex.getMessage(), ex);
                }

                Instant endingJsonProcess = Instant.now();
                kpisInfoData.addJsonProcessingDuration(
                        Duration.between(startingJsonProcess, endingJsonProcess).toMillis() );

                rowsCounter++;
                if(logger.isDebugEnabled()) {
                    logger.debug("Successfully finished processing of row no: " + rowsCounter);
                }
            }
            kpisInfoData.incrementProcessedFilesNumber();

            // final mapping into metricsInfoData
                // the share of KO result to OK result
                metricsInfoData.setKoToOkRatio(koCallsCounter / (float) okCallsCounter);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Ending of one day file processing.");
        }

        // implementing of kafka producer -> simulates producing into the topic in param
        try {
            kafkaSimpleProducer.produceToTopic("myTopic");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // implementing of kafka consumer -> simulates consuming back from the topic in param
        try {
            kafkaSimpleConsumer.consumeFromTopic("myTopic");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("Ending to parse and returning Metrics data.");
        }

        return metricsInfoData;
    }

    private void processWordOccurrenceRanking (String smsText) {
        for (String particularWord : smsText.split(" ") ) {
            metricsInfoData.incrementGivenWordsRanking(particularWord);
        }
    }

}
