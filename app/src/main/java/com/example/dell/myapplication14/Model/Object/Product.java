package com.example.dell.myapplication14.Model.Object;

import java.io.Serializable;

public class Product implements Serializable {
    int Product_id,Price,Quantity,Category_id,Trademark_id,Count_buy,INVENTORYNUMBER;
    String Product_name,Big_image,Small_image,Descrption;
    byte[]image;
    Discount_detail discount_detail;

    public Discount_detail getDiscount_detail() {
        return discount_detail;
    }

    public void setDiscount_detail(Discount_detail discount_detail) {
        this.discount_detail = discount_detail;
    }

    public int getINVENTORYNUMBER() {
        return INVENTORYNUMBER;
    }

    public void setINVENTORYNUMBER(int INVENTORYNUMBER) {
        this.INVENTORYNUMBER = INVENTORYNUMBER;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(int category_id) {
        Category_id = category_id;
    }

    public int getTrademark_id() {
        return Trademark_id;
    }

    public void setTrademark_id(int trademark_id) {
        Trademark_id = trademark_id;
    }

    public int getCount_buy() {
        return Count_buy;
    }

    public void setCount_buy(int count_buy) {
        Count_buy = count_buy;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getBig_image() {
        return Big_image;
    }

    public void setBig_image(String big_image) {
        Big_image = big_image;
    }

    public String getSmall_image() {
        return Small_image;
    }

    public void setSmall_image(String small_image) {
        Small_image = small_image;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String descrption) {
        Descrption = descrption;
    }
}
