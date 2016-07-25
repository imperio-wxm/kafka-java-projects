package wxmimperio.kafka.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxmimperio.kafka.common.ParamsConst;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by weiximing.imperio on 2016/7/25.
 */
public class PropertyUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtil.class);

    private static String propFile = System.getProperty("user.dir") + "/conf/" + ParamsConst.CONFIG_FILE_NAME;

    private PropertyUtil() {
    }

    private static Properties prop = new Properties();

    static {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(propFile));
            prop.load(in);
        } catch (Exception e) {
            LOG.error("init properties happen error ", e);
        }
    }

    ///////////////////////////////// kafka props start /////////////////////////////////

    /**
     * 获取ZK连接IP列表
     *
     * @return list
     */
    public static List<String> getZKConnectList() {
        List<String> list = StringUtil.splitStr2ListByComma(prop.getProperty(ParamsConst.ZOOKEEPER_CONNECT));
        return list;
    }

    /**
     * 获取GroupId列表
     *
     * @return list
     */
    public static List<String> getGroupIdList() {
        List<String> list = StringUtil.splitStr2ListByComma(prop.getProperty(ParamsConst.GROUP_ID));
        return list;
    }

    /**
     * 获取Topic列表
     *
     * @return list
     */
    public static List<String> getTopicList() {
        List<String> list = StringUtil.splitStr2ListByComma(prop.getProperty(ParamsConst.TOPICS));
        return list;
    }

    public static String getZookeeperSessionTimeout() {
        return prop.getProperty(ParamsConst.ZOOKEEPER_SESSION_TIMEOUT);
    }

    public static String getZookeeperSyncTime() {
        return prop.getProperty(ParamsConst.ZOOKEEPER_SYNC_TIME);
    }

    public static String getAutoCommitInterval() {
        return prop.getProperty(ParamsConst.AUTO_COMMIT_INTERVAL);
    }

    public static String getAutoOffsetReset() {
        return prop.getProperty(ParamsConst.AUTO_OFFSET_RESET);
    }

    public static String getSerializedClass() {
        return prop.getProperty(ParamsConst.SERIALIZER_CLASS);
    }

    public static String getThreads() {
        return prop.getProperty(ParamsConst.THREADS);
    }

    ///////////////////////////////// kafka props end /////////////////////////////////
}
