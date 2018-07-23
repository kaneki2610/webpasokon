package com.example.dell.myapplication14.View.ProductDetail.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.myapplication14.Adapter.DigitalInfoAdapter;
import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailActivity;


import java.util.ArrayList;
import java.util.List;

public class ProductComment extends Fragment {
    List<DigitalInfo> infoList;
    DigitalInfoAdapter adapter;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.product_info_fragment_layout,container,false);
        AddControl(view);
        ProductDetailActivity p= (ProductDetailActivity) getActivity();
        infoList=p.getDigital();

       // Log.d("info",infoList.get(0).getName()+infoList.get(0).getValue());
        adapter=new DigitalInfoAdapter(getActivity(),R.layout.custom_line_digital_info,infoList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void AddControl(View view) {
        infoList=new ArrayList<>();
        listView=view.findViewById(R.id.listviewDigital);
    }
}
