package com.example.dell.myapplication14.View.HomePage.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dell.myapplication14.Adapter.DiscountAdapter;
import com.example.dell.myapplication14.Model.Object.Discount;
import com.example.dell.myapplication14.Presenter.Discount.DiscountPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.HomePage.ViewFragment.DiscountViewImp;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentHighlights extends Fragment implements DiscountViewImp {
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    DiscountAdapter discountAdapter;
    DiscountPresenterLogic discountPresenterLogic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.highlight_fragment_layout,container,false);
        AddControl(view);
        discountPresenterLogic=new DiscountPresenterLogic(this);
        discountPresenterLogic.getListDiscount();
        return view;
    }

    private void AddControl(View view) {
        linearLayout=view.findViewById(R.id.linearDiscount);
        recyclerView=view.findViewById(R.id.recycleDiscount);
    }

    @Override
    public void DisplayListDiscount(List<Discount> list) {

        int count_image=list.size();
        if(count_image>4)
        {
            count_image=4;
        }else{
            count_image=list.size();
        }
        linearLayout.removeAllViews();
        for(int i=0;i<count_image;i++)
        {
            ImageView imageView=new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,250);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);
            //Log.d("kt2",list.get(i).getIMAGE());
            Picasso.with(getContext()).load(list.get(i).getIMAGE()).resize(1080,250).into(imageView);
            linearLayout.addView(imageView);
        }

        discountAdapter=new DiscountAdapter(getContext(),list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(discountAdapter);
        discountAdapter.notifyDataSetChanged();
    }
}
