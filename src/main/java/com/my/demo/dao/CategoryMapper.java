package com.my.demo.dao;

import com.my.demo.doo.CategoryDo;
import com.my.demo.entity.Category;
import com.my.demo.query.CategoryQueryModel;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 查询分类信息
     *
     * @param queryModel 查询参数
     * @return
     */
    List<CategoryDo> findCategoryInfo(CategoryQueryModel queryModel);
}