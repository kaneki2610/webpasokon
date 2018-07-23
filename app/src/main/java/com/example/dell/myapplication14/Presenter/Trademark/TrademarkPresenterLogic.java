package com.example.dell.myapplication14.Presenter.Trademark;

import com.example.dell.myapplication14.Model.Object.Trademark;
import com.example.dell.myapplication14.Model.Trademark.TrademarkModel;
import com.example.dell.myapplication14.View.HomePage.ViewFragment.TrademarkViewImp;

import java.util.List;

public class TrademarkPresenterLogic implements TrademarkImp {
    TrademarkViewImp trademarkViewImp;
    TrademarkModel trademarkModel;
    public TrademarkPresenterLogic(TrademarkViewImp trademarkViewImp)
    {
        this.trademarkViewImp=trademarkViewImp;
        trademarkModel=new TrademarkModel();

    }
    @Override
    public void getListBigTrademark() {
        List<Trademark> list=trademarkModel.getListTrademark();
        if(list.size()>0)
        {
            trademarkViewImp.DisplayTrademark(list);
        }
    }
}
