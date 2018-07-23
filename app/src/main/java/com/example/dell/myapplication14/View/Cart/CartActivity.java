package com.example.dell.myapplication14.View.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.myapplication14.Adapter.CartAdapter;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.Cart.CartPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.InfoCustomer.InfoCustomerActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartViewImp, View.OnClickListener {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    Button btn;
    static TextView txt;
    TextView txtEm;
    static List<Product> products;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_layout);
        AddControl();
        AddEvent();


        CartPresenterLogic cartPresenterLogic = new CartPresenterLogic(this);
        cartPresenterLogic.getListProduct(this);
        Test();
    }

    private void AddEvent() {
        btn.setOnClickListener(this);
    }

    private void AddControl() {
        recyclerView = findViewById(R.id.recycleView);
        btn = findViewById(R.id.btnFinish);
        txt = findViewById(R.id.txtTien);
        txtEm = findViewById(R.id.txtEmpty);
        products = new ArrayList<>();
    }


    @Override
    public void getListCart(List<Product> list) {
        products = list;
        if (list.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            txtEm.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            cartAdapter = new CartAdapter(this, list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
        } else {
            txtEm.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
        }


    }

    public static void Test() {

        long tt = 0;
        int sl = 0;
        int gia = 0;
        long toal = 0;
        for (int i = 0; i < products.size(); i++) {
            sl = products.get(i).getQuantity();
            gia = products.get(i).getPrice();
            tt = sl * gia;
            toal += tt;
        }
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(toal).toString();
        txt.setText("Toal" + "(" + products.size() + " item" + ")" + " : " + price + " VNĐ");
    }

    @Override
    public void AddCartSuccess() {


    }

    @Override
    public void AddCartFail() {

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(CartActivity.this, InfoCustomerActivity.class);
        startActivity(intent);
    }
}
