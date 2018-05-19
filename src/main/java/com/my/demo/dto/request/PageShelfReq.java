package com.my.demo.dto.request;

import com.google.common.collect.Lists;
import com.my.demo.doo.CardInfoDo;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PageShelfReq :   货架
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/26 10:22
 */
@Data
public class PageShelfReq {

    /**
     * 货架id
     */
    private String page_id = "KxVEt/Azyo4+qjETrwkDvserF8TXajROkXOnYEThO3Y=";

    /**
     * banner
     */
    private String banner_pic_url = "http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0";

    /**
     * 主题列表
     */
    private List<PageThemeInfo> theme_list;

    /**
     * 货架主题信息
     */
    @Data
    public static class PageThemeInfo {
        /**
         * 主题图片
         */
        private String theme_pic_url;

        /**
         * 主题名称
         */
        private String title;

        /**
         * 主题色
         */
        private String title_color = "#EA393C";

        /**
         *  卡列表
         */
        private List<CardInfoDo> item_list;


        /**
         * 图片列表
         */
        private List<Map> pic_item_list = Lists.newArrayList(
                new HashMap(){{
                    put("background_pic_url","http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8u5FRNibh0DLnN2xUp76W2E5tD7RV6ia6BEfic2BUnJ3dvG0hgsqdd2L0KpDpmKu3oVibyuCfooWh6z6A/0");
                    put("default_gifting_msg", "默认祝福语1");
                }},
                new HashMap(){{
                    put("background_pic_url","http://mmbiz.qpic.cn/mmbiz_jpg/AKLoY2Mvic8tz9lK8Q07Hrs62H2KX6KYy3PInQWEHthv0r5RCgBzRR3rcSlIjic2L3ibodeoWFqrH46qicKlK047vA/0");
                    put("default_gifting_msg", "默认祝福语2");
                }}
        );


    }


}
