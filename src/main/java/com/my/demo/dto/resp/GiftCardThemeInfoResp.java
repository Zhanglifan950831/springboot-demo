package com.my.demo.dto.resp;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * GiftCardThemeInfoResp :  礼品卡主题信息Resp
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/22 09:50
 */
@Data
public class GiftCardThemeInfoResp {

    /**
     * 主题id
     */
    private Integer themeId;

    /**
     * 主题名称
     */
    private String title;

    /**
     * 面额列表
     */
    private List<Integer> priceList;

    /**
     * 卡面列表
     */
    private List<String> cardPicList = Lists.newArrayList(
            "http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0",
            "http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0"
    );
}
