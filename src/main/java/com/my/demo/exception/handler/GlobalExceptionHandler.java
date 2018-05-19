package com.my.demo.exception.handler;

import com.my.demo.exception.PlatformException;
import com.my.demo.result.Result;
import com.my.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * GlobalExceptionHandler : 统一异常处理
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 10:23
 */
@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    /** 日志记录 */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {PlatformException.class})
    @ResponseBody
    public Result handler(Exception e){
        if (e instanceof PlatformException){
            PlatformException platformException = (PlatformException) e;
            return ResultUtil.error(platformException.getCode(), platformException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(-1,"未知错误");
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (body instanceof  Result) {
            return body;
        }
        return ResultUtil.success(body);
    }
}
