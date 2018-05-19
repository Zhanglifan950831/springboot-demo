package com.my.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.my.demo.dao.PicResourceMapper;
import com.my.demo.entity.AccessToken;
import com.my.demo.entity.PicResource;
import com.my.demo.service.DemoService;
import com.my.demo.util.DateTimeUtil;
import com.my.demo.util.HttpServiceUtil;
import com.my.demo.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * DemoServiceImpl :
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/20 20:42
 */
@Service(value = "demoService")
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Value("${weixin.AppID}")
    private String appId;

    @Value("${weixin.AppSecret}")
    private String appSecret;

    /**
     * 获取AccessToken的请求地址
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={AppID}&secret={AppSecret}";

    @Autowired
    private PicResourceMapper mapper;

    @Override
    public PicResource test() {
        PicResource resource = mapper.selectByPrimaryKey(1);
        logger.info("查询数据库获取的值：{}", JsonUtil.toJson(resource));
        return resource;
    }

    private AccessToken getToken() {
        String requestUrl = ACCESS_TOKEN_URL.replace("{AppID}", appId).replace("{AppSecret}", appSecret);
        JSONObject result = HttpServiceUtil.doGet(requestUrl);
        AccessToken token = new AccessToken();
        token.setToken(result.getString("access_token"));
        token.setExpiresIn(result.getLong("expires_in"));
        token.setRequestTime(DateTimeUtil.currentTimeStamp());

        return token;
    }

    @Override
    @Cacheable(cacheNames = "token")
    public AccessToken getAccessToken() {

        return getToken();
    }

    @CachePut(cacheNames = "token")
    @Override
    public AccessToken upDateAccessToken() {

        return getToken();
    }

}
