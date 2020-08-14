package cz.centrum.haffner.SimpleTrainingDavid.Kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Properties;

// this class automatically fills property values from application.properties file into Properties class
@Component
@PropertySource("classpath:application.properties")
public class PropertiesFiller {

    // config values from application.properties
    @Value("${bootstrap.servers}")
    private String BOOTSTRAP_SERVERS;
    @Value("${group.id}")
    private String GROUP_ID;
    @Value("${enable.auto.commit}")
    private String ENABLE_AUTO_COMMIT;
    @Value("${auto.commit.interval.ms}")
    private String AUTO_COMMIT_INTERVAL_MS;
    @Value("${session.timeout.ms}")
    private String SESSION_TIMEOUT_MS;
    @Value("${key.deserializer}")
    private String KEY_DESERIALIZER;
    @Value("${value.deserializer}")
    private String VALUE_DESERIALIZER;

    @Value("${acknowledgement}")
    private String ACKNOWLEDGEMENT;
    @Value("${retries}")
    private String RETRIES;
    @Value("${batch.size}")
    private String BATCH_SIZE;
    @Value("${linger.ms}")
    private String LINGER_MS;
    @Value("${buffer.memory}")
    private String BUFFER_MEMORY;
    @Value("${key.serializer}")
    private String KEY_SERIALIZER;
    @Value("${value.serializer}")
    private String VALUE_SERIALIZER;

    private Properties properties = new Properties();


    @PostConstruct
    public void init () {
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        properties.put("group.id", GROUP_ID);
        properties.put("enable.auto.commit", ENABLE_AUTO_COMMIT);
        properties.put("auto.commit.interval.ms", AUTO_COMMIT_INTERVAL_MS);
        properties.put("session.timeout.ms", SESSION_TIMEOUT_MS);
        properties.put("key.deserializer", KEY_DESERIALIZER);
        properties.put("value.deserializer", VALUE_DESERIALIZER);

        properties.put("acknowledgement", ACKNOWLEDGEMENT);
        properties.put("retries", RETRIES);
        properties.put("batch.size", BATCH_SIZE);
        properties.put("linger.ms", LINGER_MS);
        properties.put("buffer.memory",BUFFER_MEMORY);
        properties.put("key.serializer", KEY_SERIALIZER);
        properties.put("value.serializer", VALUE_SERIALIZER);
    }

    public Properties getProperties() { return this.properties; }
}
