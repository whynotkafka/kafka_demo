package whykafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;

//import util.properties packages
import java.util.Properties;
import java.util.UUID;


public class JSONConsumer {

    public static void main (String[] args) throws Exception {

        String topicName = "tweets";
        // Check arguments length value
        if(topicName.length() == 0){
            System.out.println("Enter topic name");
        }

        Properties properties = new Properties();


        properties.put("bootstrap.servers", System.getenv("KAFKA_BOOTSTRAP_SERVERS"));
        properties.put("security.protocol", System.getenv("KAFKA_SECURITY_PROTOCOL"));
        properties.put("ssl.truststore.location", System.getenv("KAFKA_SSL_TRUSTSTORE_LOCATION"));
        properties.put("ssl.truststore.password", System.getenv("KAFKA_SSL_TRUSTSTORE_PASSWORD"));
        properties.put("ssl.keystore.type", System.getenv("KAFKA_SSL_KEYSTORE_TYPE"));
        properties.put("ssl.keystore.location", System.getenv("KAFKA_SSL_KEYSTORE_LOCATION"));
        properties.put("ssl.keystore.password", System.getenv("KAFKA_SSL_KEYSTORE_PASSWORD"));
        properties.put("ssl.key.password", System.getenv("KAFKA_SSL_KEY_PASSWORD"));
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "1");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Collections.singletonList("tweets"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    System.out.println(record.key());
                    System.out.println(record.value());
                }
            }
        }

        finally {
            consumer.close();
            System.out.println("Message received successfully");
        }

    }
}