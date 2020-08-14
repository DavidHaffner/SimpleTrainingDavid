package cz.centrum.haffner.SimpleTrainingDavid.Kafka;

import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// this class simulates the role of Producer into Kafka Topic / system
@Component
public class KafkaSimpleProducer {
    private static final Logger logger = LogManager.getLogger(KafkaSimpleProducer.class);

    @Autowired
    PropertiesFiller propertiesFiller;

    // testing producer with no data input from application
    public void produceToTopic(String topicName) {
        logger.debug("Starting to produce to topic: {}", topicName);

        // create instance of Producer with properties
        Producer<String, String> producer = new KafkaProducer<>( propertiesFiller.getProperties() );

        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>( topicName, String.valueOf(i), String.valueOf(i) ));
            logger.info("KAFKA Producer created successfully msg text: {}", i);
        }

        producer.close();
    }

    // producer which transforms input object into JSON form and produces it
    public void produceToTopic(String topicName, Object dataObject) {
        logger.debug("Starting to produce to topic: {}", topicName);

        // create instance of Producer with properties
        Producer<String, String> producer = new KafkaProducer<>( propertiesFiller.getProperties() );

        // converting object into json string
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(dataObject);
            // producing into Kafka
            producer.send(new ProducerRecord<>( topicName, jsonString, jsonString ));
            logger.info("KAFKA Producer created successfully msg text: {}", jsonString);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        } finally {
            producer.close();
        }
    }
}
