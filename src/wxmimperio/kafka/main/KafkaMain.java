package wxmimperio.kafka.main;

import wxmimperio.kafka.consumer.KafkaConsumer;
import wxmimperio.kafka.utils.PropertyUtil;

import java.util.List;

public class KafkaMain {

    public static void main(String[] args) {
        List<String> zkList = PropertyUtil.getZKConnectList();
        List<String> groupIdList = PropertyUtil.getGroupIdList();
        List<String> topicList = PropertyUtil.getTopicList();

        for(String zk : zkList) {
            for(String groupId : groupIdList) {
                for (String topic : topicList) {
                    KafkaConsumer consumer = new KafkaConsumer(zk, groupId, topic);
                    consumer.run(Integer.valueOf(PropertyUtil.getThreads()));
                }
            }
        }
    }
}
