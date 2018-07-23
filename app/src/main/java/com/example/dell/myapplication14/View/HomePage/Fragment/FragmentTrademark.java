package com.example.dell.myapplication14.View.HomePage.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.myapplication14.Adapter.BigTrademarkAdapter;
import com.example.dell.myapplication14.Model.Object.Trademark;
import com.example.dell.myapplication14.Presenter.Trademark.TrademarkPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.HomePage.ViewFragment.TrademarkViewImp;

import java.util.List;

public class FragmentTrademark extends Fragment implements TrademarkViewImp {
    RecyclerView recyclerView;
    BigTrademarkAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.trademark_fragment_layout,container,false);
        AddControl(view);
        TrademarkPresenterLogic trademarkPresenterLogic=new TrademarkPresenterLogic(this);
        trademarkPresenterLogic.getListBigTrademark();
        return view;
    }

    private void AddControl(View view) {
        recyclerView=view.findViewById(R.id.recycle_trademark);
    }

    @Override
    public void DisplayTrademark(List<Trademark> list) {
        adapter=new BigTrademarkAdapter(getContext(),list);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
