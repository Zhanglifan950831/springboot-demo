package com.my.demo.dto.resp;

import lombok.Data;

/**
 * GiftCardDetailResp : 礼品卡详情Resp
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 14:54
 */
@Data
public class GiftCardDetailResp {

    private String  title = "测试礼品卡详情";

    private String cardPic = "http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0";

    private Integer amount = 1;

    /**
     * 使用商品
     */
    private String commodity = "创纪云商品（纯属测试）";

    /**
     * 有效时间
     */
    private String validTime = "2019/05/18";

    /**
     * 礼品卡号
     */
    private String code = "CJY121389527";

    /**
     * 状态
     */
    private Integer status = 5;

    /**
     * 状态文字
     */
    private String statusText = "已领取";

    /**
     * 获取时间
     */
    private String getTime = "2018/05/18 12:20:15";

    private Integer orderNo = 121389527;

    /**
     * 下单时间
     */
    private String orderTime = "2018/05/18 11:30:25";

}
