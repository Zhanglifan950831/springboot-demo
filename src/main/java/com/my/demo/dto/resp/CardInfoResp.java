package com.my.demo.dto.resp;

import lombok.Data;

import java.util.List;

/**
 * CardInfoResp :   卡信息Resp
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/24 18:13
 */
@Data
public class CardInfoResp {

    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 卡列表
     */
    private List<GiftCardInfo> cardList;

    @Data
    public static class GiftCardInfo {
        /**
         * 礼品卡面额
         */
        private Integer amount;

        /**
         * 礼品卡id
         */
        private String cardId;

    }
}
