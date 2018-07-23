package com.example.dell.myapplication14.Presenter.Cart;

import android.content.Context;

import com.example.dell.myapplication14.Model.Object.Product;

public interface CartPresenterImp {
    void addCart(Product product, Context context);
    void getListProduct(Context context);

}
