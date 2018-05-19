package com.my.demo.doo;

import com.my.demo.entity.ThemeConfig;
import lombok.Data;

import java.util.List;

/**
 * CategoryDo : 分类信息Do
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 15:35
 */
@Data
public class CategoryDo {
    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    /**
     * 主题配置列表
     */
    private List<ThemeConfig> themeConfigList;
}
