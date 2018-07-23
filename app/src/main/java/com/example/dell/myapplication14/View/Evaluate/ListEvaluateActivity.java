package com.example.dell.myapplication14.View.Evaluate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.dell.myapplication14.Adapter.EvaluateAdapter;
import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.ProductDetail.ProductDetailPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailViewImp;

import java.util.List;

public class ListEvaluateActivity extends AppCompatActivity implements ProductDetailViewImp {
    RecyclerView recyclerView;
    EvaluateAdapter evaluateAdapter;
    ProductDetailPresenterLogic productDetailPresenterLogic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_evaluate_layout);
        AddControl();
        int product_id = getIntent().getIntExtra("product_id", 0);
        productDetailPresenterLogic = new ProductDetailPresenterLogic(this);
        productDetailPresenterLogic.getListEvaluate(product_id, 0);
    }

    private void AddControl() {
        recyclerView = findViewById(R.id.recycleView);
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

    @Override
    public void DisplayEvaluate(List<Evaluate> list) {
        evaluateAdapter=new EvaluateAdapter(this,list,0);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(evaluateAdapter);
        evaluateAdapter.notifyDataSetChanged();
    }
}
