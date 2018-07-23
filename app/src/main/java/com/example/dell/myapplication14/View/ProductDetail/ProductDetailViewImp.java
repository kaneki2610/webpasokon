package com.example.dell.myapplication14.View.ProductDetail;

import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;

import java.util.List;

public interface ProductDetailViewImp {
    void DisplayProduct(Product product);
    void DisplayDigitalInfo(List<DigitalInfo> list);
    void DisplaySliderProduct(String[]image_link);
    void DisplayEvaluate(List<Evaluate>list);
}
