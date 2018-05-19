package com.my.demo.ctrl;

import com.my.demo.dto.request.CardCreateReq;
import com.my.demo.dto.resp.CardInfoResp;
import com.my.demo.dto.resp.GiftCardDetailResp;
import com.my.demo.dto.resp.GiftCardHomeInfoResp;
import com.my.demo.dto.resp.GiftCardThemeInfoResp;
import com.my.demo.exception.PlatformException;
import com.my.demo.query.OrderHistoryQueryModel;
import com.my.demo.service.DemoService;
import com.my.demo.service.GiftCardService;
import com.my.demo.util.DateTimeUtil;
import com.my.demo.util.JsonUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * GiftCardController : 礼品卡Controller
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 14:47
 */
@Controller("giftCardController")
//@RestController("giftCardController") // 等效于@Controlelr与@ResponseBody
@RequestMapping(value = "giftCard")
public class GiftCardController {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(GiftCardController.class);


    /**
     * 礼品卡服务
     */
    @Autowired
    private GiftCardService giftCardService;

    @Autowired
    private DemoService demoService;

    /**
     * 获取礼品卡首页信息
     *
     * @return 礼品卡首页信息
     */
    @ResponseBody
    @GetMapping(value = "home")
    public GiftCardHomeInfoResp getHomeInfo() {

        return giftCardService.getHomeInfo();
    }

    /**
     * 通过主题id获取相关信息
     *
     * @param themeId 主题id
     * @return 主题相关信息
     */
    @ResponseBody
    @GetMapping(value = "{themeId}/info")
    public GiftCardThemeInfoResp getThemeInfo(@PathVariable("themeId") Integer themeId) {

        return giftCardService.getThemeInfo(themeId);
    }

    /**
     * 获取礼品卡详情
     *
     * @param cardId 礼品卡id
     * @return 礼品卡详情
     */
    @ResponseBody
    @GetMapping(value = "card/{cardId}/detail")
    public GiftCardDetailResp getCardInfoDetail(@PathVariable("cardId") Integer cardId) {
        GiftCardDetailResp resp = new GiftCardDetailResp();
        return resp;
    }

    /**
     * 获取订单列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "order/list")
    public String getOrderList(OrderHistoryQueryModel queryModel) {
        System.out.println("时间为：" + DateTimeUtil.getTimeStr(1524450206, "YYYY/MM/dd HH:mm:ss"));
        logger.info(JsonUtil.toJson(queryModel));
//        demoService.getAccessToken();
        return null;
    }


    /**
     * 创建礼品卡接口（同时生成订单）
     *
     * @param req 请求体
     * @return
     */
    @ResponseBody
    @PostMapping(value = "create/card")
    public CardInfoResp createCard(@RequestBody CardCreateReq req) {
        return giftCardService.createCard(req);
    }


    /**
     * 微信支付回掉
     *
     * @param request
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "微信支付回调")
    @RequestMapping(value = "wxpay/callback", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/xml")
    public String wxpayCallBack(HttpServletRequest request) {

        logger.info("进入微信支付回调了");
        String resXml = "";
        String result = "";
        try {
            InputStream is = request.getInputStream();
            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            resXml = sb.toString();
            /** 微信回调返回结果 */
            result = giftCardService.wxpayCallBack(resXml);
        } catch (IOException e) {
            logger.error("微信支付回调失败：{}", e);
            result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            e.printStackTrace();
        }

        return result;
    }


}
