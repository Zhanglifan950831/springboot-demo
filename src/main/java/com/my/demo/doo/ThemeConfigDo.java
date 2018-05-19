package com.my.demo.doo;

import lombok.Data;

import java.util.List;

/**
 * ThemeConfigDo :  主题配置Do
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/22 09:59
 */
@Data
public class ThemeConfigDo {

    /**
     * 主题id
     */
    private Integer id;

    /**
     * 主题名称
     */
    private String title;

    /**
     * 面额列表
     */
    private List<Integer> priceList;
}
