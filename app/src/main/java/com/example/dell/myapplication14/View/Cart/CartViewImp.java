package com.example.dell.myapplication14.View.Cart;

import com.example.dell.myapplication14.Model.Object.Product;

import java.util.List;

public interface CartViewImp {
    void AddCartSuccess();
    void AddCartFail();
    void getListCart(List<Product> list);
}
