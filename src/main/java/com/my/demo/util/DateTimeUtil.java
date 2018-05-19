package com.my.demo.util;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTimeUtil :   时间基础类
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/23 10:19
 */
public class DateTimeUtil {

    /**
     * 获取当前时间戳
     *
     * @return 当前时间错
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取指定日期格式的时间戳对应时间字符串
     *
     * @param timeStamp  时间戳
     * @param dateFormat 时间格式字符串
     * @return
     */
    public static String getTimeStr(long timeStamp, String dateFormat) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        return simpleDateFormat.format(new Date(timeStamp * 1000));
    }

}
