package cz.centrum.haffner.SimpleTrainingDavid;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Extractor;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.OneDayFileJsonParser;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Parser;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleConsumer;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;

// this class tests the parse() method by using data from file MCP_20180131.json as an input
@RunWith(MockitoJUnitRunner.class)
public class OneDayFileJsonParserTest {
    private static final Logger logger = LogManager.getLogger(OneDayFileJsonParserTest.class);
    private static final String FILE_PATH = "https://raw.githubusercontent.com/TomasStesti/simpleTraining/master/logs/MCP_20180131.json";
    @InjectMocks
    Parser testedParser = new OneDayFileJsonParser();
    @Mock
    KpisInfoData kpisInfoData;
    @Mock
    Extractor countryCodeExtractor;
    @Mock
    KafkaSimpleProducer kafkaSimpleProducer;
    @Mock
    KafkaSimpleConsumer kafkaSimpleConsumer;

    MetricsInfoData dataResult = new MetricsInfoData();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest(){
        try {
            URL testFileUrl = new URL(FILE_PATH);
            dataResult = testedParser.parse(testFileUrl);

            logger.debug( "Test of parse() >> result data:  \n" + dataResult.toString() );

            // TODO: final assertion
            Assertions.assertTrue(true);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
