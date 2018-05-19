package com.my.demo.dao;

import com.my.demo.entity.OrderInfoDetail;

public interface OrderInfoDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfoDetail record);

    int insertSelective(OrderInfoDetail record);

    OrderInfoDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfoDetail record);

    int updateByPrimaryKey(OrderInfoDetail record);
}