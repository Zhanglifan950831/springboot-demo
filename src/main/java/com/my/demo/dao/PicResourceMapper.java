package com.my.demo.dao;

import com.my.demo.entity.PicResource;

public interface PicResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PicResource record);

    int insertSelective(PicResource record);

    PicResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PicResource record);

    int updateByPrimaryKey(PicResource record);
}