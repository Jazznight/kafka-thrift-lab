package tv.migo.lab;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by brian on 1/28/14.
 */


public class KafkaThriftTest {
    public static void main(String[] args){

        Properties props = new Properties();
        props.put("zookeeper.connect", "keeper01:2181");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("group.id", "test_group");

        // Create the connection to the cluster
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        //topicCountMap.put("raw_prototyping", new Integer(1));
        topicCountMap.put("raw", new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        //KafkaStream<byte[], byte[]> stream =  consumerMap.get("raw_prototyping").get(0);
        KafkaStream<byte[], byte[]> stream =  consumerMap.get("raw").get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while(it.hasNext())
            System.out.println(new String(it.next().message()));
            /*
            for(MessageAndMetadata msgAndMetadata: stream) {
                // process message (msgAndMetadata.message())

            }
            */


    }
}
