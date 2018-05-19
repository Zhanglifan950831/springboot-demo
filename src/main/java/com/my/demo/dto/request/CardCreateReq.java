package com.my.demo.dto.request;

import lombok.Data;

import java.util.List;

/**
 * CardCreateReq :  创建礼品卡Req
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/24 18:14
 */
@Data
public class CardCreateReq {

    /**
     * 主题id
     */
    private Integer themeId;

    /**
     * 卡面
     */
    private String cardPic;

    /**
     * 总金额
     */
    private int totalFee;

    private List<CardInfo> cardList;

    @Data
    public static class CardInfo {

        /**
         * 数量
         */
        private Integer count;

        /**
         * 卡面额
         */
        private Integer value;
    }
}
