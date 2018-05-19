package com.my.demo.dto.resp;

import com.google.common.collect.Lists;
import com.my.demo.entity.Category;
import lombok.Data;

import java.util.List;

/**
 * GiftCardHomeInfoResp :   礼品卡首页信息Resp
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 14:52
 */
@Data
public class GiftCardHomeInfoResp {

    /**
     * banner
     */
    private String banner;

    /**
     * 类型列表
     */
    private List<CategoryInfo> categoryList = Lists.newArrayList();

    @Data
    public static class CategoryInfo {
        /**
         * 分类名称
         */
        private String name;

        /**
         * 子标题
         */
        private String subTitle;

        /**
         * 主题列表
         */
        private List<ThemeInfo> themeList = Lists.newArrayList();
    }

    @Data
    public static class ThemeInfo {

        /**
         * 主题id
         */
        private Integer themeId;

        /**
         * 主题名
         */
        private String title;

        /**
         * 主题图片地址
         */
        private String themePicUrl;

    }
}
