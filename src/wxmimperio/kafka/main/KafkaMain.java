package wxmimperio.kafka.main;

import wxmimperio.kafka.consumer.Consumer;
import wxmimperio.kafka.utils.PropertyUtil;

import java.util.List;

public class KafkaMain {

    public static void main(String[] args) {
        List<String> zkList = PropertyUtil.getZKConnectList();
        List<String> groupIdList = PropertyUtil.getGroupIdList();
        List<String> topicList = PropertyUtil.getTopicList();

        for(String zk : zkList) {
            for(String topic : topicList) {
                for (String groupId : groupIdList) {
                    Consumer consumer = new Consumer(zk, groupId, topic);
                    consumer.run(Integer.valueOf(PropertyUtil.getThreads()));
                }
            }
        }
    }
}
