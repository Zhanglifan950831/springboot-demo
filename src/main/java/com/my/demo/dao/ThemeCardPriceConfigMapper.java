package com.my.demo.dao;

import com.my.demo.entity.ThemeCardPriceConfig;

public interface ThemeCardPriceConfigMapper {
    int insert(ThemeCardPriceConfig record);

    int insertSelective(ThemeCardPriceConfig record);
}