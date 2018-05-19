package com.my.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * HttpServiceUtil :    Http服务基础类
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/24 11:44
 */
public class HttpServiceUtil {

    public static JSONObject doGet(String requestUrl) {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        /** 获取DefaultHttpClient请求 */
        DefaultHttpClient client = new DefaultHttpClient();
        /** 使用GET方式发送请求地址 */
        HttpGet httpGet = new HttpGet(requestUrl);
        JSONObject jsonObject = null;
        try {
            /** 接收client执行httpGet的结果 */
            HttpResponse response = client.execute(httpGet);
            /** 从response中获取结果,类型为HttpEntity */
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                /** HttpEntity转为字符串类型 */
                String result = EntityUtils.toString(entity, "UTF-8");
                /** 字符串转换为jsonObject */
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPost(String requestUrl, String outStr) {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        /** 获取DefaultHttpClient请求 */
        DefaultHttpClient client = new DefaultHttpClient();
        /** 使用POST方式发送请求地址 */
        HttpPost httpPost = new HttpPost(requestUrl);
        JSONObject jsonObject = null;
        try {
            /** 使用setEntity方法，将参数加入请求中 */
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            /** 接收client执行httpPost的结果 */
            HttpResponse response = client.execute(httpPost);
            /** HttpEntity转为字符串类型 */
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            /** 字符串转换为jsonObject */
            jsonObject = JSONObject.parseObject(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
