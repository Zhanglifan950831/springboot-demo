package com.my.demo.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;

/**
 * JsonUtil :   json工具类
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/3/29 15:27
 */
public final class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json
     *
     * @param o 需要转换为json的对象
     * @return
     */
    public static String toJson(Object o) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 将json字符串转为对象
     *
     * @param json  json字符串
     * @param clazz 对象的class
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (Strings.isNotEmpty(json)) {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
