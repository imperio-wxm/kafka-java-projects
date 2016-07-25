package wxmimperio.kafka.consumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weiximing.imperio on 2016/7/22.
 */
public class ConsumerHandle implements Runnable{

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerHandle.class);
    private KafkaStream stream;
    private int threadNumber;

    public ConsumerHandle(KafkaStream stream, int threadNumber) {
        this.stream = stream;
        this.threadNumber = threadNumber;
    }

    public void run() {
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            //Metadata is a message
            MessageAndMetadata metadata = it.next();
            System.out.println("Thread " + threadNumber + ": " + metadata.message() + " offset: " +
                                metadata.offset() + " partition: " + metadata.partition() + " topic：" +
                                metadata.topic());
        }
        LOG.info("Shutting down Thread: " + threadNumber);
    }
}
