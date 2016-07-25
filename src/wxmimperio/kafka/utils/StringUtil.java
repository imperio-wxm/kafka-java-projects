package wxmimperio.kafka.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiximing.imperio on 2016/7/25.
 */
public class StringUtil {
    public static List<String> splitStr2ListByComma(String str) {
        List<String> list = new ArrayList<String>();
        String[] strList = str.split(",");
        for (String string : strList) {
            list.add(string);
        }
        return list;
    }
}
