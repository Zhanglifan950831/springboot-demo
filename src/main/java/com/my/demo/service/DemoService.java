package com.my.demo.service;

import com.my.demo.entity.AccessToken;
import com.my.demo.entity.PicResource;

/**
 * DemoService :
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/20 20:42
 */
public interface DemoService {

    PicResource test();

    AccessToken getAccessToken();

    AccessToken upDateAccessToken();

}
