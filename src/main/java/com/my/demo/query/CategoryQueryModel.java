package com.my.demo.query;

import lombok.Data;

/**
 * CategoryQueryModel : 分类查询model
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/21 15:43
 */
@Data
public class CategoryQueryModel {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 审核状态
     */
    private Integer checkStatus;

}
