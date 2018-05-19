package com.my.demo.entity;

public class ThemeConfig {
    private Integer id;

    private String title;

    private String themePicUrl;

    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThemePicUrl() {
        return themePicUrl;
    }

    public void setThemePicUrl(String themePicUrl) {
        this.themePicUrl = themePicUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}