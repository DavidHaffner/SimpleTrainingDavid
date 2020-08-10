package cz.centrum.haffner.SimpleTrainingDavid.Kafka;

import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

// this class simulates the role of Consumer from Kafka Topic / system
@Component
public class KafkaSimpleConsumer {
    private static final Logger logger = LogManager.getLogger(KafkaSimpleConsumer.class);

    public static void consumeFromTopic(String topicName) throws Exception {

        if(logger.isDebugEnabled()) {
            logger.debug("Starting to consume from topic: " + topicName);
        }

        // create instance for properties to access producer configs
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        boolean haveNewRecords = false;
        while ( !haveNewRecords) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {

                // print the offset,key and value for the consumer records.
                if (logger.isInfoEnabled()) {
                    logger.info("Consumed from topic: " + topicName
                                    + "\noffset = " + record.offset()
                                    + ", key = " + record.key()
                                    + ", value = " + record.value() );
                }
            }

            if (records.partitions().size() != 0) { haveNewRecords = true; }
        }
    }
}
