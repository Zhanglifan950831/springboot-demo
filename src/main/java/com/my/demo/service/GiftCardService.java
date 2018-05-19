package com.my.demo.service;

import com.my.demo.dto.request.CardCreateReq;
import com.my.demo.dto.resp.CardInfoResp;
import com.my.demo.dto.resp.GiftCardHomeInfoResp;
import com.my.demo.dto.resp.GiftCardThemeInfoResp;

/**
 * GiftCardService :    礼品卡Service
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 15:20
 */
public interface GiftCardService {

    /**
     * 获取礼品卡首页信息
     *
     * @return 首页信息
     */
    GiftCardHomeInfoResp getHomeInfo();

    /**
     * 通过主题id获取主题信息
     *
     * @param themeId 主题id
     * @return 主题信息
     */
    GiftCardThemeInfoResp getThemeInfo(Integer themeId);

    /**
     * 创建礼品卡
     *
     * @param req 请求体
     * @return
     */
    CardInfoResp createCard(CardCreateReq req);

    /**
     * 微信支付回掉
     *
     * @param resXml 微信回调Xml
     * @return
     */
    String wxpayCallBack(String resXml);

}
