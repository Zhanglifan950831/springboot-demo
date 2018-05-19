package com.my.demo.result;

import lombok.Data;

/**
 * Result : 统一结果返回
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 10:29
 */
@Data
public class Result<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;
}
