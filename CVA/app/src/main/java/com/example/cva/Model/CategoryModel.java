package com.example.cva.Model;

public class CategoryModel {
    private String title;
    private String picture;

    public CategoryModel() {}

    public CategoryModel(String title, String picture) {
        this.title = title;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
