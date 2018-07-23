package com.example.dell.myapplication14.View.InfoCustomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.Object.Bill;
import com.example.dell.myapplication14.Model.Object.Bill_Detail;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.Payment.PaymentPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.HomePage.HomePageActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoCustomerActivity extends AppCompatActivity implements PaymentViewImp, View.OnClickListener {
    EditText edtName, edtPhone, edtAddress;
    ImageButton imFast, imVisa;
    Button btnFinishpayment;
    CheckBox checkBox;
    PaymentPresenterLogic paymentPresenterLogic;
    List<Bill_Detail>billDetailList=new ArrayList<>();
    int select=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_customer_cart_layout);
        AddControl();
        AddEvent();
        paymentPresenterLogic=new PaymentPresenterLogic(this,this);
        paymentPresenterLogic.getListProductinCart();
    }

    private void AddEvent() {
        btnFinishpayment.setOnClickListener(this);
        imFast.setOnClickListener(this);
        imVisa.setOnClickListener(this);
    }

    private void AddControl() {
        edtName = findViewById(R.id.edtNameKH);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        imFast = findViewById(R.id.imDeliveryFast);
        imVisa = findViewById(R.id.imVisa);
        btnFinishpayment = findViewById(R.id.btnFinishPayment);
        checkBox = findViewById(R.id.chkDongY);
    }

    @Override
    public void PaymentSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(InfoCustomerActivity.this,HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void PaymentFail() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnFinishPayment:
                String name=edtName.getText().toString();
                String phone=edtPhone.getText().toString();
                String address=edtAddress.getText().toString();
                if(name.trim().length()>0&&phone.trim().length()>0&&address.trim().length()>0)
                {
                    if(checkBox.isChecked())
                    {
                        Bill bill=new Bill();
                        bill.setNAME(name);
                        bill.setADDRESS(address);
                        bill.setPHONE(phone);
                        bill.setTRANSFER(select);
                        bill.setBill_detailList(billDetailList);
                       paymentPresenterLogic.addBill(bill);
                    }else{
                        Toast.makeText(this, "Bạn chưa đồng ý thỏa thuận", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imDeliveryFast:
                Toast.makeText(this, "Bạn đã chọn thanh toán khi nhận hàng!", Toast.LENGTH_SHORT).show();
                select=0;
                break;
            case R.id.imVisa:
                Toast.makeText(this, "Bạn đã chọn thanh toán qua thẻ tín dụng!", Toast.LENGTH_SHORT).show();
                select=1;
                break;
        }
    }
    @Override
    public void getListProductInCart(List<Product> list) {
        Log.d("hung",list.size()+"");
        for(int i=0;i<list.size();i++)
        {
            Bill_Detail bill_detail=new Bill_Detail();
            bill_detail.setProduct_id(list.get(i).getProduct_id());
            bill_detail.setQuantity(list.get(i).getQuantity());
            billDetailList.add(bill_detail);
        }
    }
}
