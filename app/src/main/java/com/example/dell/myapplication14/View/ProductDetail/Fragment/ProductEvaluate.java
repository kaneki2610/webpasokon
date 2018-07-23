package com.example.dell.myapplication14.View.ProductDetail.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dell.myapplication14.Adapter.EvaluateAdapter;
import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.ProductDetail.ProductDetailPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.Evaluate.EvaluateActivity;
import com.example.dell.myapplication14.View.Evaluate.ListEvaluateActivity;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailActivity;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailViewImp;

import java.util.ArrayList;
import java.util.List;

public class ProductEvaluate extends Fragment implements View.OnClickListener, ProductDetailViewImp {
    TextView txtWriteEvaluate, txtSeeAllEvaluate;
    RecyclerView recyclerView;
    int product_id = 0;
    public static final int RESULT_CODE_EVALUATE = 100;
    ProductDetailPresenterLogic productDetailPresenterLogicl;
    EvaluateAdapter evaluateAdapter;
    List<Evaluate>evaluateList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_evaluate_fragment, container, false);
        AddControl(view);
        AddEvent();
        ProductDetailActivity p = (ProductDetailActivity) getActivity();
        product_id = p.getProudctId();
        productDetailPresenterLogicl = new ProductDetailPresenterLogic(this);
        productDetailPresenterLogicl.getListEvaluate(product_id, 0);
        return view;
    }

    private void AddEvent() {
        txtWriteEvaluate.setOnClickListener(this);
        txtSeeAllEvaluate.setOnClickListener(this);
    }

    private void AddControl(View view) {
        txtWriteEvaluate = view.findViewById(R.id.txtWriteEvaluate);
        txtSeeAllEvaluate = view.findViewById(R.id.txtSeeAllEvaluate);
        recyclerView = view.findViewById(R.id.recycle_Evaluate);
        evaluateList=new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.txtWriteEvaluate:
                Intent intent = new Intent(getActivity(),EvaluateActivity.class);
                intent.putExtra("product_id", product_id);
                startActivityForResult(intent, RESULT_CODE_EVALUATE);
                break;
            case R.id.txtSeeAllEvaluate:
                Intent intent1=new Intent(getActivity(), ListEvaluateActivity.class);
                intent1.putExtra("product_id",product_id);
                startActivity(intent1);
                break;
        }
    }
    @Override
    public void DisplayEvaluate(List<Evaluate> list) {
        //Log.d("hu",list.get(0).getContent());
        evaluateList=list;
        evaluateAdapter=new EvaluateAdapter(getContext(),list,3);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(evaluateAdapter);
        evaluateAdapter.notifyDataSetChanged();
    }
    //recive result from Evaluate.Activity

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_CODE_EVALUATE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                boolean result=data.getBooleanExtra("test",true);
                if(result)
                {

                    productDetailPresenterLogicl.getListEvaluate(product_id, 0);
                }
            }
        }
    }
    @Override
    public void DisplayProduct(Product product) {

    }

    @Override
    public void DisplayDigitalInfo(List<DigitalInfo> list) {

    }

    @Override
    public void DisplaySliderProduct(String[] image_link) {

    }

}
