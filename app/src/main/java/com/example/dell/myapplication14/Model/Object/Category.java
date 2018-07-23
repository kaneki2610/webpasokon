package com.example.dell.myapplication14.Model.Object;

import java.util.List;

public class Category {
    private int Category_id;
    private int Parent_id;
    private String Category_name;
    private List<Category> listChild;

    public Category(int category_id, int parent_id, String category_name) {
        Category_id = category_id;
        Parent_id = parent_id;
        Category_name = category_name;
    }

    public Category() {
    }

    public int getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(int category_id) {
        Category_id = category_id;
    }

    public int getParent_id() {
        return Parent_id;
    }

    public void setParent_id(int parent_id) {
        Parent_id = parent_id;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public List<Category> getListChild() {
        return listChild;
    }

    public void setListChild(List<Category> listChild) {
        this.listChild = listChild;
    }
}
