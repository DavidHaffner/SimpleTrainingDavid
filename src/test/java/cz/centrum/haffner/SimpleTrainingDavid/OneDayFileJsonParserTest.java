package cz.centrum.haffner.SimpleTrainingDavid;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.CountryCodeExtractor;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Extractor;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.OneDayFileJsonParser;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Parser;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.CountryCodeMap;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleConsumer;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

// this class tests the parse() method by using data from file MCP_20180131.json as an input
@RunWith(SpringRunner.class)
public class OneDayFileJsonParserTest {
    private static final Logger logger = LogManager.getLogger(OneDayFileJsonParserTest.class);
    private static final String FILE_PATH = "https://raw.githubusercontent.com/TomasStesti/simpleTraining/master/logs/MCP_20180131.json";

    @TestConfiguration
    static class KafkaTestContextConfiguration {
        @Bean
        public Parser testedParser() {
            return new OneDayFileJsonParser();
        }
        @Bean
        public Extractor countryCodeExtractor() {
            return new CountryCodeExtractor();
        }
        @Bean
        public CountryCodeMap countryCodeMap() {
            return new CountryCodeMap();
        }
    }
    @Autowired
    private Parser testedParser;
    @MockBean
    private KafkaSimpleProducer kafkaSimpleProducer;
    @MockBean
    private KafkaSimpleConsumer kafkaSimpleConsumer;
    @MockBean
    private KpisInfoData kpisInfoData;

    private MetricsInfoData dataResult = new MetricsInfoData();


    @Before
    public void setUp() {
        // following methods are not necessary for testing of Parser.parse()
        doNothing().when(kafkaSimpleProducer).produceToTopic("myTopic");
        doNothing().when(kafkaSimpleConsumer).consumeFromTopic("myTopic");
        doNothing().when(kpisInfoData).incrementTotalCallsNumber();
        doNothing().when(kpisInfoData).incrementTotalMessagesNumber();
        doNothing().when(kpisInfoData).incrementTotalRowsNumber();
        doNothing().when(kpisInfoData).addDifferentOriginCodesSet( isA(Integer.class) );
        doNothing().when(kpisInfoData).addDifferentDestinationCodesSet( isA(Integer.class) );
        doNothing().when(kpisInfoData).addJsonProcessingDuration( isA(Long.class) );
        doNothing().when(kpisInfoData).incrementProcessedFilesNumber();
    }

    @Test
    public void parseTest(){
        try {
            URL testFileUrl = new URL(FILE_PATH);
            dataResult = testedParser.parse(testFileUrl);

            // logger.debug( "Test of parse() >> result data:  " + dataResult.toString() );

            // "MetricsInfoData{missingFieldsRowsCounter=13, blankContentMessagesCounter=4, fieldsErrorsRowsCounter=47,
            // groupedCallsOriginCounter={49=6, 34=11, 44=6}, groupedCallsDestinationCounter={49=6, 34=11, 44=6},
            // koToOkRatio=0.29411766, groupedAverageCallDuration={49=12.5, 34=145.71428, 44=3.3333333},
            // givenWordsRanking={HI=3, HELLO=12, YOU?=1, TODAY?=1, HOW=1, SURE=1, ARE=1, 1.1=3, 2.1=1, 3.1=1, 5.=1, FINE=1, 3.=1, LUNCH=1, 2.=1, GOOD=1, 1.=12}}
            Assertions.assertEquals(13, dataResult.getMissingFieldsRowsCounter() );
            Assertions.assertEquals(4, dataResult.getBlankContentMessagesCounter() );
            Assertions.assertEquals(47, dataResult.getFieldsErrorsRowsCounter() );
            Assertions.assertEquals(6, dataResult.getGroupedCallsOriginCounter().get(49) );
            Assertions.assertEquals(11, dataResult.getGroupedCallsOriginCounter().get(34) );
            Assertions.assertEquals(6, dataResult.getGroupedCallsOriginCounter().get(44) );
            Assertions.assertEquals(6, dataResult.getGroupedCallsDestinationCounter().get(49) );
            Assertions.assertEquals(11, dataResult.getGroupedCallsDestinationCounter().get(34) );
            Assertions.assertEquals(6, dataResult.getGroupedCallsDestinationCounter().get(44) );
            Assertions.assertEquals(0.29411766f, dataResult.getKoToOkRatio() );
            Assertions.assertEquals(12.5f, (float)dataResult.getGroupedAverageCallDuration().get(49) );
            Assertions.assertEquals(145.71428f, (float)dataResult.getGroupedAverageCallDuration().get(34) );
            Assertions.assertEquals(3.3333333f, (float)dataResult.getGroupedAverageCallDuration().get(44) );
            Assertions.assertEquals(3, dataResult.getGivenWordsRanking().get("HI") );
            Assertions.assertEquals(12, dataResult.getGivenWordsRanking().get("HELLO") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("YOU?") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("TODAY?") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("HOW") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("SURE") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("ARE") );
            Assertions.assertEquals(3, dataResult.getGivenWordsRanking().get("1.1") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("2.1") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("3.1") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("5.") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("FINE") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("3.") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("LUNCH") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("2.") );
            Assertions.assertEquals(1, dataResult.getGivenWordsRanking().get("GOOD") );
            Assertions.assertEquals(12, dataResult.getGivenWordsRanking().get("1.") );

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
