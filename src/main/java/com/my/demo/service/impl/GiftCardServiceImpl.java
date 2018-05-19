package com.my.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Lists;
import com.my.demo.dao.*;
import com.my.demo.doo.CardInfoDo;
import com.my.demo.doo.CategoryDo;
import com.my.demo.doo.ThemeConfigDo;
import com.my.demo.dto.request.CardCreateReq;
import com.my.demo.dto.request.PageShelfReq;
import com.my.demo.dto.resp.CardInfoResp;
import com.my.demo.dto.resp.GiftCardHomeInfoResp;
import com.my.demo.dto.resp.GiftCardThemeInfoResp;
import com.my.demo.entity.*;
import com.my.demo.exception.PlatformException;
import com.my.demo.query.CardInfoQueryModel;
import com.my.demo.query.CategoryQueryModel;
import com.my.demo.service.DemoService;
import com.my.demo.service.GiftCardService;
import com.my.demo.util.DateTimeUtil;
import com.my.demo.util.HttpServiceUtil;
import com.my.demo.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GiftCardServiceImpl :    礼品卡ServiceImpl
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 15:21
 */
@Service(value = "giftCardService")
public class GiftCardServiceImpl implements GiftCardService {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(GiftCardServiceImpl.class);

    /**
     * 未发送状态
     */
    private static final Integer UNSEND_STATUS = 0;

    /**
     * 分类信息Mapper
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 主题信息Mapper
     */
    @Autowired
    private ThemeConfigMapper themeConfigMapper;

    @Autowired
    private DemoService demoService;

    /**
     * 卡信息Mapper
     */
    @Autowired
    private CardInfoMapper cardInfoMapper;

    /**
     * 订单信息Mapper
     */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 订单详情Mapper
     */
    @Autowired
    private OrderInfoDetailMapper orderInfoDetailMapper;

    @Override
    public GiftCardHomeInfoResp getHomeInfo() {
        GiftCardHomeInfoResp resp = new GiftCardHomeInfoResp();
        /** 查询审核通过的分类信息列表 */
        CategoryQueryModel queryModel = new CategoryQueryModel();
        queryModel.setCheckStatus(2);
        List<CategoryDo> list = categoryMapper.findCategoryInfo(queryModel);
        /** 创建Resp内部的分类列表 */
        List<GiftCardHomeInfoResp.CategoryInfo> categoryInfoList = Lists.newArrayList();
        /** 根据查询到的结果对Resp内部的分类列表进行赋值 */
        list.forEach(item -> {
            GiftCardHomeInfoResp.CategoryInfo categoryInfo = new GiftCardHomeInfoResp.CategoryInfo();
            categoryInfo.setName(item.getName());
            categoryInfo.setSubTitle(item.getSubTitle());
            /** 分类相关的主题列表 */
            List<GiftCardHomeInfoResp.ThemeInfo> themeInfoList = Lists.newArrayList();
            /**
             * 遍历查询到的与分类相关的主题列表
             */
            item.getThemeConfigList().forEach(e -> {
                GiftCardHomeInfoResp.ThemeInfo themeInfo = new GiftCardHomeInfoResp.ThemeInfo();
                themeInfo.setThemeId(e.getId());
                themeInfo.setTitle(e.getTitle());
                themeInfo.setThemePicUrl(e.getThemePicUrl());
                themeInfoList.add(themeInfo);
            });
            categoryInfo.setThemeList(themeInfoList);
            categoryInfoList.add(categoryInfo);
        });
        resp.setCategoryList(categoryInfoList);
        return resp;
    }

    @Override
    public GiftCardThemeInfoResp getThemeInfo(Integer themeId) {

        logger.info("主题id：{}", themeId);
        ThemeConfigDo themeConfigDo = themeConfigMapper.getThemeInfo(themeId);
        logger.info("查询数据库返回结果:{}", JsonUtil.toJson(themeConfigDo));
        /** 实例化返回对象 */
        GiftCardThemeInfoResp resp = new GiftCardThemeInfoResp();
        resp.setThemeId(themeConfigDo.getId());
        resp.setTitle(themeConfigDo.getTitle());
        resp.setPriceList(themeConfigDo.getPriceList());
        return resp;
    }

    @Override
    public CardInfoResp createCard(CardCreateReq req) {
        CardInfoResp resp = new CardInfoResp();

        AccessToken token = demoService.getAccessToken();

        long currentTime = DateTimeUtil.currentTimeStamp();
        if (currentTime - token.getRequestTime() > token.getExpiresIn()) {
            token = demoService.upDateAccessToken();
        }

        Integer themeId = req.getThemeId();

        /** 获取生成的订单号 */
        String orderNo = orderInfoMapper.generateOrderNo();
        logger.info("生成的订单号为：{}", orderNo);
        /** 新增订单 */
        OrderInfo order = new OrderInfo();
        order.setThemeId(themeId);
        order.setOrderStatus(1);    // 待支付
        order.setTotalFee(req.getTotalFee());
        order.setCardPic(req.getCardPic());
        order.setOrderNo(orderNo);
        order.setOrderTime(currentTime);
        order.setSendStatus(UNSEND_STATUS); // 未赠送
        int orderId = orderInfoMapper.insert(order);
        logger.info("生成的订单id为：{}", orderId);
        /**
         * 主题信息
         */
        ThemeConfig themeInfo = themeConfigMapper.selectByPrimaryKey(themeId);
        resp.setThemeName(themeInfo.getTitle());
        resp.setOrderNo(orderNo);

        List<CardInfoResp.GiftCardInfo> cardInfoList = Lists.newArrayList();

        String createCardUrl = "https://api.weixin.qq.com/card/create?access_token={ACCESS_TOKEN}".replace("{ACCESS_TOKEN}", token.getToken());
        req.getCardList().forEach(item -> {


            CardInfoResp.GiftCardInfo giftCardInfo = new CardInfoResp.GiftCardInfo();
            Integer amount = item.getValue();
            giftCardInfo.setAmount(amount);
            String cardId;

            /**
             * 增加订单详情记录
             */
            OrderInfoDetail orderDetail = new OrderInfoDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setAmount(amount);
            orderDetail.setSendState(UNSEND_STATUS);
            orderDetail.setCardName("创纪云礼品卡");
            orderInfoDetailMapper.insert(orderDetail);

            /**
             * 查询主题下相应面额的礼品卡是否存在
             */
            CardInfoQueryModel queryModel = new CardInfoQueryModel();
            queryModel.setAmount(amount);
            queryModel.setThemeId(themeId);
            List<CardInfo> list = cardInfoMapper.slectCardList(queryModel);
            /**
             * 如果卡信息中已有该面值的卡，则直接取值，否则创建卡
             */
            if (list.size() > 0) {
                cardId = list.get(0).getCardId();
            } else {
                /** 请求数据 */
                String requestBody = "{\n" +
                        "    \"card\": {\n" +
                        "        \"card_type\": \"GENERAL_CARD\",\n" +
                        "        \"general_card\": {\n" +
                        "            \"sub_card_type\": \"GIFT_CARD\",\n" +
                        "            \"base_info\": {\n" +
                        "                \"max_give_friend_times\": 1,\n" +
                        "                \"giftcard_info\": {\n" +
                        "                    \"price\": " + amount + "\n" +
                        "                },\n" +
                        "                \"logo_url\": \"https://mmbiz.qlogo.cn/mmbiz/p98FjXy8LaeMq67mEpDmkj05EtiaVcGOibVaVux3Agib1ibcHFkCoic7HuQWFawx9XGCNWIO085drjdxTib2nBHlYGAA/0?wx_fmt=gif\",\n" +
                        "                \"brand_name\": \"测试商户\",\n" +
                        "                \"code_type\": \"CODE_TYPE_QRCODE\",\n" +
                        "                \"title\": \"创纪云礼品卡\",\n" +
                        "                \"color\": \"Color020\",\n" +
                        "                \"notice\": \"使用时向服务员出示\",\n" +
                        "                \"service_phone\": \"020-88888888\",\n" +
                        "                \"description\": \"不可与其他优惠同享\",\n" +
                        "                \"date_info\": {\n" +
                        "                    \"type\": \"DATE_TYPE_PERMANENT\"\n" +
                        "                },\n" +
                        "                \"sku\": {\n" +
                        "                    \"quantity\": 50000000\n" +
                        "                },\n" +
                        "                \"get_limit\": 0,\n" +
                        "                \"use_custom_code\": false,\n" +
                        "                \"can_give_friend\": true,\n" +
                        "                \"need_push_on_view\": true\n" +
                        "            },\n" +
                        "            \"supply_bonus\": false,\n" +
                        "            \"supply_balance\": false,\n" +
                        "            \"prerogative\": \"礼品卡享受更多优惠\",\n" +
                        "            \"auto_activate\": true\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
                JSONObject result = HttpServiceUtil.doPost(createCardUrl, requestBody);
                /** 错误码 */
                Integer errorCode = result.getInteger("errcode");
                if (errorCode.equals(0)) {
                    CardInfo record = new CardInfo();
                    record.setThemeId(themeId);
                    record.setAmount(amount);
                    cardId = result.getString("card_id");
                    record.setCardId(cardId);
                    cardInfoMapper.insert(record);
                } else {
                    throw new PlatformException(errorCode, result.getString("errmsg"));
                }
            }
            giftCardInfo.setCardId(cardId);
            cardInfoList.add(giftCardInfo);
        });

        resp.setCardList(cardInfoList);

        JSONObject updatePageShelfResult = HttpServiceUtil.doPost("https://api.weixin.qq.com/card/giftcard/page/update?access_token=" + token.getToken(), getPageShelfRequestBody());
        System.out.println(updatePageShelfResult.toJSONString());
        return resp;
    }

    @Override
    public String wxpayCallBack(String resXml) {
        String xmlBack = "";
        /** 需要转换xml的Map */
        Map<String, String> notifyMap = null;

        try {
            notifyMap = WXPayUtil.xmlToMap(resXml);
            /** 状态 */
            String return_code = notifyMap.get("return_code");
            /** 订单号 */
            String out_trade_no = notifyMap.get("out_trade_no");

            if (return_code.equals("SUCCESS")) {
                if (out_trade_no != null) {
                    System.err.println(">>>>>支付成功");

                    /** 通过订单号查询订单信息 */
                    OrderInfo orderInfo = orderInfoMapper.getOrderByOrderNo(out_trade_no);
                    /** 支付成功更新订单状态 */
                    orderInfo.setOrderStatus(2);
                    /** 设置购买人openId */
                    orderInfo.setOpenid(notifyMap.get("openid"));
                    orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
                    logger.info("微信支付回调成功订单号:{}", out_trade_no);
                    xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                } else {
                    logger.info("微信支付回调失败订单号:{}", out_trade_no);
                    xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
            }
        } catch (Exception e) {
            logger.error("手机回调通知失败", e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            e.printStackTrace();
        }
        return xmlBack;
    }

    private String getPageShelfRequestBody() {
        PageShelfReq req = new PageShelfReq();

        List<PageShelfReq.PageThemeInfo> themeList = Lists.newArrayList();

        /** 查询审核通过的分类信息列表 */
        CategoryQueryModel queryModel = new CategoryQueryModel();
        queryModel.setCheckStatus(2);
        List<CategoryDo> list = categoryMapper.findCategoryInfo(queryModel);
        list.forEach(item -> {
            item.getThemeConfigList().forEach(e -> {
                Integer themeId = e.getId();
                /** 获取对应主题相关联创建的卡 */
                List<CardInfoDo> cardIdList = cardInfoMapper.selectCardByThemeId(themeId);
                if (cardIdList.size() > 0) {
                    PageShelfReq.PageThemeInfo pageThemeInfo = new PageShelfReq.PageThemeInfo();
                    pageThemeInfo.setTitle(e.getTitle());
                    pageThemeInfo.setTheme_pic_url(e.getThemePicUrl());
                    pageThemeInfo.setItem_list(cardIdList);
                    themeList.add(pageThemeInfo);
                }
            });
        });
        req.setTheme_list(themeList);

        Map<String, Object> map = new HashMap<>();
        map.put("page", req);
        return JsonUtil.toJson(map);
    }
}
