package com.example.dell.myapplication14.View.Evaluate;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Presenter.Evaluate.EvaluatePresenterLogic;
import com.example.dell.myapplication14.R;

public class EvaluateActivity extends AppCompatActivity implements EvaluateViewImp,View.OnClickListener,RatingBar.OnRatingBarChangeListener{
    int product_id = 0;
    int number_star = 0;
    String evaluate_id = "";
    String device_name = "";
    private final int MY_PERMISSIONS_REQUEST_CODE = 1;
    EditText edtTiltle, edtContent;
    Button btnCommit;
    RatingBar ratingBar;
    TextInputLayout inputTitle, inputContent;
    EvaluatePresenterLogic evaluatePresenterLogic;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_product_layout);
        AddControl();
        AddEvent();
        product_id = getIntent().getIntExtra("product_id", 0);
        Toast.makeText(this,product_id+"",Toast.LENGTH_LONG).show();

        if (checkPermissions()) {
            startApplication();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Permissions");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setPermissions();
                }
            });
            builder.show();
        }
        evaluatePresenterLogic = new EvaluatePresenterLogic(this);
        Log.d("product_id", product_id + "");
    }

    private void AddEvent() {
        ratingBar.setOnRatingBarChangeListener(this);
        btnCommit.setOnClickListener(this);
    }

    private void AddControl() {
        edtTiltle = findViewById(R.id.txtTitleBarEvaluate);
        edtContent = findViewById(R.id.txtContentBarEvaluate);
        btnCommit = findViewById(R.id.btnYes);
        ratingBar = findViewById(R.id.ratingBarEvaluate);
        inputTitle = findViewById(R.id.inputLayoutTitle);
        inputContent = findViewById(R.id.inputLayoutContent);
    }

    @Override
    public void SuccessEvaluate() {
        Toast.makeText(this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.putExtra("test",true);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void FailEvaluate() {
        Toast.makeText(this, "Bạn không thể dánh giá sản phẩm này", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        number_star = (int) rating;
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSIONS_REQUEST_CODE) {
            return;
        }
        boolean isGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }

        if (isGranted) {
            startApplication();
        } else {
            Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();

        }
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_CODE);
    }

    @SuppressWarnings("deprecation")
    private String getIMEINumber() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                evaluate_id = telephonyMgr.getImei();
            } else {
                evaluate_id = telephonyMgr.getDeviceId();
            }
        }
        return evaluate_id;
    }

    public void startApplication() {

        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        evaluate_id =getIMEINumber();
        device_name = Build.MODEL;
    }

    @Override
    public void onClick(View view) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);


        String title = edtTiltle.getText().toString();
        String content = edtContent.getText().toString();
        if (title.trim().length() > 0) {
            inputTitle.setErrorEnabled(false);
            inputTitle.setError("");
        } else {
            inputTitle.setErrorEnabled(true);
            inputTitle.setError("Bạn chưa nhập tiêu đề");
        }
        if (content.trim().length() > 0) {
            inputContent.setErrorEnabled(false);
            inputContent.setError("");

        } else {
            inputContent.setErrorEnabled(true);
            inputContent.setError("Bạn chưa nhập nội dung");
        }
        if (!inputTitle.isErrorEnabled() && !inputContent.isErrorEnabled()) {
            Evaluate evaluate = new Evaluate();
            evaluate.setEvaluate_id(evaluate_id);
            evaluate.setProduct_id(product_id);
            evaluate.setTitle(title);
            evaluate.setContent(content);
            evaluate.setDevice_name(device_name);
            evaluate.setNumber_star(number_star);
            evaluatePresenterLogic.addEvaluation(evaluate);
            //finish();
        }

    }

}

