package wxmimperio.kafka.consumer;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxmimperio.kafka.common.ParamsConst;
import wxmimperio.kafka.utils.PropertyUtil;

/**
 * Created by weiximing.imperio on 2016/7/22.
 */
public class Consumer {
    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;


    public Consumer(String zookeeper, String groupId, String topic) {
        this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig(zookeeper, groupId));
        this.topic = topic;
    }

    //Init conf
    private static ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
        Properties props = new Properties();
        props.put(ParamsConst.ZOOKEEPER_CONNECT, zookeeper);
        props.put(ParamsConst.GROUP_ID, groupId);
        props.put(ParamsConst.ZOOKEEPER_SESSION_TIMEOUT, PropertyUtil.getZookeeperSessionTimeout());
        props.put(ParamsConst.ZOOKEEPER_SYNC_TIME, PropertyUtil.getZookeeperSyncTime());
        props.put(ParamsConst.AUTO_COMMIT_INTERVAL, PropertyUtil.getAutoCommitInterval());
        //fix offset
        props.put(ParamsConst.AUTO_OFFSET_RESET, PropertyUtil.getAutoOffsetReset());
        //serialize
        props.put(ParamsConst.SERIALIZER_CLASS, PropertyUtil.getSerializedClass());

        LOG.info("Consumer Info: [zk=" + zookeeper + " group=" + groupId +"]");

        return new ConsumerConfig(props);
    }

    public void run(int numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(numThreads));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        //Create streams and get topic
        Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        List<KafkaStream<String, String>> streams = consumerMap.get(topic);

        //ThreadPool
        executor = Executors.newFixedThreadPool(numThreads);

        //Consume Message
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            //Handle Message
            executor.submit(new ConsumerHandle(stream, threadNumber));
            threadNumber++;
        }
    }
}
