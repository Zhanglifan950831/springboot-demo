package com.my.demo.ctrl;

import com.my.demo.entity.AccessToken;
import com.my.demo.entity.PicResource;
import com.my.demo.exception.PlatformException;
import com.my.demo.result.Result;
import com.my.demo.service.DemoService;
import com.my.demo.util.DateTimeUtil;
import com.my.demo.util.JsonUtil;
import com.my.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController :
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/20 20:37
 */
@Controller("demoController")
@RequestMapping(value = "demo")
public class DemoController {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @GetMapping(value = "list", produces = "application/json;charset=UTF-8")
    public PicResource test() {
        PicResource PicResource = this.demoService.test();

        AccessToken token = demoService.getAccessToken();
        logger.info("测试缓存{}", JsonUtil.toJson(token));

        long currentTime = DateTimeUtil.currentTimeStamp();
        if (currentTime - token.getRequestTime() > token.getExpiresIn()) {
            logger.info("重新获取token");
            token = demoService.upDateAccessToken();
        }
        logger.info("缓存为：{}", JsonUtil.toJson(demoService.getAccessToken()));
        return PicResource;
    }

}
