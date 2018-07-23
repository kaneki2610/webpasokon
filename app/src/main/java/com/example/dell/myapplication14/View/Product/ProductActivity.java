package com.example.dell.myapplication14.View.Product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.myapplication14.Adapter.ProductAdapter;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.Product.ProductPresenterLogic;
import com.example.dell.myapplication14.R;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductViewImp{
    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressBar progressBar;
    int trademark_id;
    ProductAdapter productAdapter;
    boolean kind_grid = true;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        AddControl();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        trademark_id = intent.getIntExtra("trademark_id", 0);
        String name=intent.getStringExtra("trademark_name");
        getSupportActionBar().setTitle(name);



        ProductPresenterLogic p=new ProductPresenterLogic(this);
        p.getListProduct(String.valueOf(trademark_id),0);
    }

    private void AddControl() {
        recyclerView = findViewById(R.id.recycleViewProduct);
        toolbar=findViewById(R.id.toolbarProduct);
        progressBar=findViewById(R.id.progress_bar);
    }

    @Override
    public void DisplayListProduct(List<Product> list) {
        //Toast.makeText(this, list.get(0).getProduct_name(), Toast.LENGTH_SHORT).show();
        layoutManager=new GridLayoutManager(ProductActivity.this,2);
        productAdapter = new ProductAdapter(ProductActivity.this, list,R.layout.custom_recycleview_line_product);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);
        //recyclerView.addOnScrollListener(new LoadMore(layoutManager,this));
        productAdapter.notifyDataSetChanged();
    }
}
