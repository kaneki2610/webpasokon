package com.example.dell.myapplication14.Presenter.Payment;

import android.content.Context;

import com.example.dell.myapplication14.Model.Cart.CartModel;
import com.example.dell.myapplication14.Model.Object.Bill;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Model.Payment.PaymentModel;
import com.example.dell.myapplication14.View.InfoCustomer.PaymentViewImp;

import java.util.List;

public class PaymentPresenterLogic implements PaymentPresenterImp {
    PaymentViewImp paymentViewImp;
    PaymentModel paymentModel;
    CartModel cartModel;
    List<Product>list;
    public PaymentPresenterLogic(PaymentViewImp paymentViewImp,Context context) {
        this.paymentViewImp = paymentViewImp;
        paymentModel = new PaymentModel();
        cartModel=new CartModel();
        cartModel.opnenConnectSQL(context);
    }

    @Override
    public void addBill(Bill bill) {

        boolean result = paymentModel.AddBill(bill);
        if(result)
        {
            paymentViewImp.PaymentSuccess();
            int count=list.size();
            for(int i=0;i<count;i++)
            {
                cartModel.deleteCart(list.get(i).getProduct_id());
            }
        }else {
            paymentViewImp.PaymentFail();
        }
    }

    @Override
    public void getListProductinCart() {

        list=cartModel.getListProductFromCart();
        if(list.size()>0)
        {
            paymentViewImp.getListProductInCart(list);
        }

    }
}
