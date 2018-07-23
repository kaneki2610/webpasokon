package com.example.dell.myapplication14.View.InfoCustomer;

import com.example.dell.myapplication14.Model.Object.Product;

import java.util.List;

public interface PaymentViewImp {
    void PaymentSuccess();
    void PaymentFail();
    void getListProductInCart(List<Product> list);
}
