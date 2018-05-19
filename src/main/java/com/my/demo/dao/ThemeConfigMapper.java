package com.my.demo.dao;

import com.my.demo.doo.ThemeConfigDo;
import com.my.demo.entity.ThemeConfig;

public interface ThemeConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThemeConfig record);

    int insertSelective(ThemeConfig record);

    ThemeConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThemeConfig record);

    int updateByPrimaryKey(ThemeConfig record);

    /**
     * 通过主题id获取相关信息
     *
     * @param id 主题id
     * @return 主题相关信息
     */
    ThemeConfigDo getThemeInfo(Integer id);
}