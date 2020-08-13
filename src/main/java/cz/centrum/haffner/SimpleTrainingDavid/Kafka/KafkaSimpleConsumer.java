package cz.centrum.haffner.SimpleTrainingDavid.Kafka;

import java.util.Arrays;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// this class simulates the role of Consumer from Kafka Topic / system
@Component
public class KafkaSimpleConsumer {
    private static final Logger logger = LogManager.getLogger(KafkaSimpleConsumer.class);

    @Autowired
    PropertiesFiller propertiesFiller;


    public void consumeFromTopic(String topicName) {
        logger.debug("Starting to consume from topic: {}", topicName);

        // create instance of Consumer with properties
        Consumer<String, String> consumer = new KafkaConsumer<>( propertiesFiller.getProperties() );

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        boolean haveNewRecords = false;
        while ( !haveNewRecords) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                // print the offset,key and value for the consumer records
                logger.info("Consumed from topic: {}\noffset = {}, key = {}, value = {}",
                            topicName, record.offset(), record.key(), record.value() );
            }

            if (records.partitions().size() != 0) { haveNewRecords = true; }
        }

        consumer.close();
    }
}
