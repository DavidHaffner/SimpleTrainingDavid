package cz.centrum.haffner.SimpleTrainingDavid.Kafka;

import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.OneDayFileJsonParser;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

// this class simulates the role of Producer into Kafka Topic / system
@Component
public class KafkaSimpleProducer {
    private static final Logger logger = LogManager.getLogger(KafkaSimpleProducer.class);

    // create instance for properties to access producer configs
    private Properties props = new Properties();

    private void fillProperties () {
        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acknowledgement", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    // testing producer with no data input from application
    public void produceToTopic(String topicName) {

        if(logger.isDebugEnabled()) {
            logger.debug("Starting to produce to topic: {}", topicName);
        }

        if (props.isEmpty()) { fillProperties(); }
        Producer<String, String> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>( topicName, String.valueOf(i), String.valueOf(i) ));
            if(logger.isInfoEnabled()) {
                logger.info("KAFKA Producer created successfully msg text: {}", i);
            }
        }

        producer.close();
    }

    // producer which transforms input object into JSON form and produce it
    public void produceToTopic(String topicName, Object dataObject) {
        logger.debug("Starting to produce to topic: {}", topicName);

        // preparations
        if (props.isEmpty()) { fillProperties(); }
        Producer<String, String> producer = new KafkaProducer<>(props);

        // converting object into json string
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(dataObject);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }

        // producing into Kafka
        producer.send(new ProducerRecord<>( topicName, jsonString, jsonString ));
        logger.info("KAFKA Producer created successfully msg text: {}", jsonString);

        producer.close();
    }
}
