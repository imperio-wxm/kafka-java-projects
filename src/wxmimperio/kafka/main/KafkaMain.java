package wxmimperio.kafka.main;

import wxmimperio.kafka.consumer.Consumer;

public class KafkaMain {

    public static void main(String[] args) {
        String zooKeeper = "192.168.18.35:2181";
        String groupId = "group_1";
        String topic = "topic_1";
        int threads = Integer.parseInt("4");
        String autoCommitInterval = "1000";

        Consumer consumer = new Consumer(zooKeeper, groupId, topic, autoCommitInterval);
        consumer.run(threads);
    }
}
