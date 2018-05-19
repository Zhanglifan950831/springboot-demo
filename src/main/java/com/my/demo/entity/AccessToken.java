package com.my.demo.entity;

import lombok.Data;

/**
 * AccessToken :
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/25 09:47
 */
@Data
public class AccessToken {

    private String token;

    private long expiresIn;

    private long requestTime;
}
