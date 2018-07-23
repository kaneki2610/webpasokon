package com.example.dell.myapplication14.Model.Object;

public class Bill_Detail {
    private int BILL_ID,Product_id,Quantity;

    public int getBILL_ID() {
        return BILL_ID;
    }

    public void setBILL_ID(int BILL_ID) {
        this.BILL_ID = BILL_ID;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
