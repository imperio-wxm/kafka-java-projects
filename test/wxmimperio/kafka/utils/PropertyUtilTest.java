package wxmimperio.kafka.utils;

import org.junit.Test;

import java.util.List;

/**
 * Created by weiximing.imperio on 2016/7/25.
 */
public class PropertyUtilTest {

    @Test
    public void getZKList() {
        List<String> zkList = PropertyUtil.getZKConnectList();
        for (String zk : zkList) {
            System.out.println(zk);
        }
    }

    @Test
    public void getGroupIdList() {
        List<String> groupIdList = PropertyUtil.getGroupIdList();
        for (String groupId : groupIdList) {
            System.out.println(groupId);
        }
    }

    @Test
    public void getTopicList() {
        List<String> topicList = PropertyUtil.getTopicList();
        for (String topic : topicList) {
            System.out.println(topic);
        }
    }
}
