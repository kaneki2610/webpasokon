package com.example.dell.myapplication14.Presenter.Discount;

import com.example.dell.myapplication14.Model.Discount.DiscountModel;
import com.example.dell.myapplication14.Model.Object.Discount;
import com.example.dell.myapplication14.View.HomePage.ViewFragment.DiscountViewImp;

import java.util.List;

public class DiscountPresenterLogic implements DiscountImp {
    DiscountModel discountModel;
    DiscountViewImp discountViewImp;
    public DiscountPresenterLogic(DiscountViewImp discountViewImp)
    {
        this.discountViewImp=discountViewImp;
        discountModel=new DiscountModel();
    }
    @Override
    public void getListDiscount() {
        List<Discount>discountList=discountModel.getListDicount();
        if(discountList.size()>0)
        {
            discountViewImp.DisplayListDiscount(discountList);
        }
    }
}
