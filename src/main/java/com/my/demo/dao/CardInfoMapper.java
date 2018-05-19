package com.my.demo.dao;

import com.my.demo.doo.CardInfoDo;
import com.my.demo.entity.CardInfo;
import com.my.demo.query.CardInfoQueryModel;

import java.util.List;

public interface CardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    CardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);

    List<CardInfo> slectCardList(CardInfoQueryModel queryModel);

    /**
     * 通过主题id获取所有的card_id列表
     *
     * @param themeId 主题id
     * @return
     */
    List<CardInfoDo> selectCardByThemeId(Integer themeId);
}