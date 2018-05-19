package com.my.demo.util;

import com.my.demo.result.Result;

/**
 * ResultUtil : 统一结果返回
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 10:36
 */
public class ResultUtil {

    /**
     * 成功的错误码
     */
    private static final Integer SUCCESS_CODE = 0;

    /**
     * 有成功返回结果的
     *
     * @param object 数据
     * @return
     */
    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    /**
     * 没有返回数据成功返回
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * @param code 错误码
     * @param msg  提示信息
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
