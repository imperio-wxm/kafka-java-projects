package wxmimperio.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.junit.Test;

import java.util.Properties;

import static java.lang.Thread.sleep;

/**
 * Created by weiximing.imperio on 2016/7/22.
 */
public class ProducerTest {

    @Test
    public void sendTest() {

        Producer<String, String> producer;
        String TOPIC = "topic_1";

        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "192.168.18.35:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        producer = new Producer<String, String>(new ProducerConfig(props));

        int messageNo = 1000;
        while (true) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<String, String>(TOPIC,key,data));
            System.out.println(data);
            messageNo ++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
