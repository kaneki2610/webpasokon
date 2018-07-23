package com.example.dell.myapplication14.Presenter.ProductDetail;

import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Model.ProductDetail.ProductDetailModel;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailViewImp;

import java.util.List;

public class ProductDetailPresenterLogic implements ProductDetailImp {
    ProductDetailViewImp productDetailViewImp;
    ProductDetailModel productDetailModel;

    public ProductDetailPresenterLogic(ProductDetailViewImp productDetailViewImp) {
        this.productDetailViewImp = productDetailViewImp;
        productDetailModel = new ProductDetailModel();
    }

    @Override
    public void getProductDetail(int id) {
        Product product = productDetailModel.getProductDetail(id);
        List<DigitalInfo> list = productDetailModel.getDigitalInfo(id);
            if(product.getProduct_id()>0)
            {
                String[] link = product.getSmall_image().split(",");
                productDetailViewImp.DisplaySliderProduct(link);
                productDetailViewImp.DisplayProduct(product);
                productDetailViewImp.DisplayDigitalInfo(list);

            }
    }

    @Override
    public void getListEvaluate(int product_id, int limit) {
        List<Evaluate> evaluateList = productDetailModel.getListEvaluate(product_id, limit);
        if (evaluateList.size() > 0) {
            productDetailViewImp.DisplayEvaluate(evaluateList);
        }
    }
}
