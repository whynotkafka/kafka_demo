package whynotkafka.demo;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;

//import util.properties packages
import java.util.Properties;
import java.util.UUID;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONArray;

public class JSONProducer {

    public boolean produceMessage(String topicName, JSONArray jsonArray) throws Exception{

        // Check arguments length value
        if(topicName.length() == 0){
            System.out.println("Enter topic name");
            return false;
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

        //add UUID
        UUID uuid = UUID.randomUUID();

        // create a producer
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        producer.send(new ProducerRecord<String, String>(topicName, uuid.toString(), jsonArray.toString()), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null){
                    System.out.println(exception.getMessage());
                }
            }
        });

        System.out.println("Message sent successfully");
        producer.close();
        return true;
    }
}