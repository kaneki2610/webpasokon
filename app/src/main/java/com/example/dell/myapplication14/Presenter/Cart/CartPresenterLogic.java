package com.example.dell.myapplication14.Presenter.Cart;

import android.content.Context;

import com.example.dell.myapplication14.Model.Cart.CartModel;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.View.Cart.CartViewImp;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailViewImp;

import java.util.List;

public class CartPresenterLogic implements CartPresenterImp {
   CartViewImp cartViewImp;
    CartModel cartModel;
    public CartPresenterLogic(CartViewImp cartViewImp) {
        this.cartViewImp = cartViewImp;
        cartModel = new CartModel();
    }
    @Override
    public void addCart(Product product, Context context) {
        cartModel.opnenConnectSQL(context);
        boolean result = cartModel.addCart(product);
        if (result) {
            cartViewImp.AddCartSuccess();
        } else {
            cartViewImp.AddCartFail();
        }
    }

    @Override
    public void getListProduct(Context context) {
        cartModel.opnenConnectSQL(context);
        List<Product> productList=cartModel.getListProductFromCart();
        if(productList.size()>0)
        {
            cartViewImp.getListCart(productList);
        }else{

        }
    }
    public int getTotalListCart(Context context)
    {
        cartModel.opnenConnectSQL(context);
        List<Product>productList=cartModel.getListProductFromCart();
        int count=productList.size();
        return count;
    }
}
