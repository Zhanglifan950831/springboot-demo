package com.my.demo.dao;

import com.my.demo.entity.OrderInfo;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    /**
     * 生成订单号
     *
     * @return 订单号
     */
    String generateOrderNo();

    /**
     * 通过订单号查询订单
     *
     * @param orderNo 订单号
     * @return
     */
    OrderInfo getOrderByOrderNo(String orderNo);
}