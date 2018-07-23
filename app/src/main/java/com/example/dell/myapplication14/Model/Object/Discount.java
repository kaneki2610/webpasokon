package com.example.dell.myapplication14.Model.Object;

import java.util.List;

public class Discount {
    int Discount_id,Category_id;
    String NAME,DATE_OPEN,DATE_CLOSE,IMAGE,Category_name;
    List<Product> productList;

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public int getDiscount_id() {
        return Discount_id;
    }

    public void setDiscount_id(int discount_id) {
        Discount_id = discount_id;
    }

    public int getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(int category_id) {
        Category_id = category_id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDATE_OPEN() {
        return DATE_OPEN;
    }

    public void setDATE_OPEN(String DATE_OPEN) {
        this.DATE_OPEN = DATE_OPEN;
    }

    public String getDATE_CLOSE() {
        return DATE_CLOSE;
    }

    public void setDATE_CLOSE(String DATE_CLOSE) {
        this.DATE_CLOSE = DATE_CLOSE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
