package com.example.dell.myapplication14.Presenter.Product;

import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Model.Product.ProductModel;
import com.example.dell.myapplication14.View.HomePage.ViewFragment.ElectronicViewImp;
import com.example.dell.myapplication14.View.Product.ProductViewImp;

import java.util.List;

public class ProductPresenterLogic implements ProductImp {
    ProductModel productModel;
    ProductViewImp productViewImp;

    public ProductPresenterLogic(ProductViewImp productViewImp) {
        this.productViewImp = productViewImp;
        productModel = new ProductModel();
    }



    @Override
    public void getListProduct(String trademark_id, int limit) {
        List<Product> productList = productModel.getListProduct(trademark_id, limit);
        if (productList.size() > 0) {
            productViewImp.DisplayListProduct(productList);
        }
    }


}
