package com.my.demo.exception;

/**
 * PlatformException :  自定义平台异常
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 10:19
 */
public class PlatformException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PlatformException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
