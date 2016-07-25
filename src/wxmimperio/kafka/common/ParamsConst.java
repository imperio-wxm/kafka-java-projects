package wxmimperio.kafka.common;


/**
 * Created by weiximing.imperio on 2016/7/25.
 */
public class ParamsConst {
    /**
     * properties
     */
    public final static String CONFIG_FILE_NAME = "config.properties";

    /**
     * kafka
     */
    public static final String ZOOKEEPER_CONNECT = "zookeeper.connect";
    public static final String GROUP_ID= "group.id";
    public static final String ZOOKEEPER_SESSION_TIMEOUT = "zookeeper.session.timeout.ms";
    public static final String ZOOKEEPER_SYNC_TIME = "zookeeper.sync.time.ms";
    public static final String AUTO_COMMIT_INTERVAL = "auto.commit.interval.ms";
    public static final String AUTO_OFFSET_RESET = "auto.offset.reset";
    public static final String SERIALIZER_CLASS = "serializer.class";
    public static final String TOPICS = "topics";
    public static final String THREADS = "threads";
}
