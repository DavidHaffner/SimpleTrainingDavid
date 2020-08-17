package cz.centrum.haffner.SimpleTrainingDavid;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.CountryCodeExtractor;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Extractor;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.OneDayFileJsonParser;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.Parser;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleConsumer;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class OneDayFileJsonParserTestConfig {

    @Bean
    @Primary
    public KpisInfoData kpisInfoData() {
        return Mockito.mock(KpisInfoData.class);
    }

    @Bean
    @Primary
    public Extractor countryCodeExtractor() {
        return Mockito.mock(CountryCodeExtractor.class);
    }

    @Bean
    @Primary
    public KafkaSimpleProducer kafkaSimpleProducer() {
        return Mockito.mock(KafkaSimpleProducer.class);
    }

    @Bean
    @Primary
    public KafkaSimpleConsumer kafkaSimpleConsumer() {
        return Mockito.mock(KafkaSimpleConsumer.class);
    }
}
