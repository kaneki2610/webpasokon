package com.example.dell.myapplication14.View.ProductDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication14.Adapter.ViewPagerProductDetail;
import com.example.dell.myapplication14.Adapter.ViewPagerSliderAdapter;
import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.Presenter.Cart.CartPresenterLogic;
import com.example.dell.myapplication14.Presenter.Product.ProductPresenterLogic;
import com.example.dell.myapplication14.Presenter.ProductDetail.ProductDetailPresenterLogic;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.Cart.CartActivity;
import com.example.dell.myapplication14.View.Cart.CartViewImp;
import com.example.dell.myapplication14.View.Product.ProductViewImp;
import com.example.dell.myapplication14.View.ProductDetail.Fragment.FragmentProductSlider;
import com.example.dell.myapplication14.View.ProductDetail.Fragment.ProductComment;
import com.example.dell.myapplication14.View.ProductDetail.Fragment.ProductEvaluate;
import com.example.dell.myapplication14.View.ProductDetail.Fragment.ProductInfo;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class ProductDetailActivity extends AppCompatActivity implements ProductDetailViewImp,ViewPager.OnPageChangeListener, View.OnClickListener,CartViewImp {
    TextView txtNameSPCT, txtPriceSPCT;
    TextView[] txtDot;
    Button btnAddCart;
    Toolbar toolbar;
    ViewPager viewPager, viewPagerimage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    LinearLayout linearLayout;
    TabLayout tabLayout;
    int peoduct_id=0;
    String description = "";
    List<Fragment> fragmentList;
    ProductDetailPresenterLogic productDetailPresenterLogic;
    ViewPagerProductDetail viewPagerProductDetail;
    List<DigitalInfo> digitalInfoList;
    Product productCart;
    CartPresenterLogic cartPresenterLogic;
    TextView txtCart;
    boolean onPause=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        AddControl();

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);


        peoduct_id=getIntent().getIntExtra("product_id",0);
        String name=getIntent().getStringExtra("product_name");
        getSupportActionBar().setTitle(name);

        //Toast.makeText(this, peoduct_id+"", Toast.LENGTH_SHORT).show();
        productDetailPresenterLogic=new ProductDetailPresenterLogic(this);
        productDetailPresenterLogic.getProductDetail(peoduct_id);
        cartPresenterLogic=new CartPresenterLogic(this);

        ProcessDisplayViewPager();
        AddDotSlider(0);
        viewPagerimage.addOnPageChangeListener(this);
        AddEvent();
    }

    private void AddEvent() {
        btnAddCart.setOnClickListener(this);
    }

    private void ProcessDisplayViewPager() {
        viewPagerProductDetail = new ViewPagerProductDetail(getSupportFragmentManager());
        viewPagerProductDetail.addFlag(new ProductInfo(), "Introducation");
        viewPagerProductDetail.addFlag(new ProductComment(), "Digital Info");
        viewPagerProductDetail.addFlag(new ProductEvaluate(), "Review");
        viewPager.setAdapter(viewPagerProductDetail);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void AddControl() {

        txtNameSPCT = findViewById(R.id.txtNameSPCT);
        txtPriceSPCT = findViewById(R.id.txtPriceSPCT);
        linearLayout = findViewById(R.id.layoutSlide);
        viewPagerimage = findViewById(R.id.viewPagerImage);
        viewPager = findViewById(R.id.viewPagerDetailProduct);
        tabLayout = findViewById(R.id.tabLayoutProductDetail);
        btnAddCart=findViewById(R.id.btnAddCart);
        toolbar=findViewById(R.id.toolbar);
        collapsingToolbarLayout=findViewById(R.id.collaps);
    }

    @Override
    public void DisplayProduct(Product product) {
        productCart=product;
        productCart.setINVENTORYNUMBER(product.getQuantity());
        txtNameSPCT.setText(product.getProduct_name());
        description=product.getDescrption();
        peoduct_id=product.getProduct_id();
        NumberFormat number = new DecimalFormat("###,###");
        String price = number.format(product.getPrice()).toString();
        txtPriceSPCT.setText(price + " VNĐ");
    }
    //send description to fragment infomation
    public String getDescrption() {
        return description;
    }
    @Override
    public void DisplayDigitalInfo(List<DigitalInfo> list) {
            digitalInfoList=list;
    }
    //send digital to fragment comment
    public List<DigitalInfo> getDigital() {
        return digitalInfoList;
    }
    //send description to fragment evaluate
    public int getProudctId() {
        return peoduct_id;
    }
    @Override
    public void DisplaySliderProduct(String[] image_link) {
        //Log.d("image",image_link[1]);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < image_link.length; i++) {
            FragmentProductSlider fragmentProductSlider = new FragmentProductSlider();
            Bundle bundle = new Bundle();
            bundle.putString("image_link", Config.SERVER + image_link[i]);
            fragmentProductSlider.setArguments(bundle);
            fragmentList.add(fragmentProductSlider);
        }
        ViewPagerSliderAdapter adapter = new ViewPagerSliderAdapter(getSupportFragmentManager(), fragmentList);
        viewPagerimage.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void DisplayEvaluate(List<Evaluate> list) {

    }

    //draw dot slider
    private void AddDotSlider(int current_position) {
        txtDot = new TextView[fragmentList.size()];
        linearLayout.removeAllViews();
        for (int i = 0; i < fragmentList.size(); i++) {
            txtDot[i] = new TextView(this);
            txtDot[i].setText(Html.fromHtml("&#8226"));
            txtDot[i].setTextSize(60);
            txtDot[i].setTextColor(getIColor(R.color.colorGray1));
            linearLayout.addView(txtDot[i]);

        }
        txtDot[current_position].setTextColor(getIColor(R.color.colorMain));
    }

    //with version >21 --> ContextCompat.getColor(this, color);
    //version <21--> getResources().getColor(color);
    private int getIColor(int color) {
        int color1 = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color1 = ContextCompat.getColor(this, color);
        } else {
            color1 = getResources().getColor(color);
        }
        return color1;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        AddDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnAddCart:
                Fragment fragment = fragmentList.get(viewPager.getCurrentItem());
                View view1 = fragment.getView();
                ImageView imageView = view1.findViewById(R.id.imghinhSanPham);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] image = byteArrayOutputStream.toByteArray();
                productCart.setQuantity(1);
                productCart.setImage(image);
                //Log.d("kiemtra", productCart.getProduct_name() + "" + productCart.getProduct_id() + "" + productCart.getPrice());
                cartPresenterLogic.addCart(productCart, this);
                break;
        }
    }

    //receive result from cartpresenterlogic
    @Override
    public void AddCartSuccess() {
        Toast.makeText(this, "Add product success", Toast.LENGTH_SHORT).show();
        txtCart.setText(String.valueOf(cartPresenterLogic.getTotalListCart(this)));

    }

    @Override
    public void AddCartFail() {
        Toast.makeText(this, "Add product fail!", Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.itemCart);
        View view = MenuItemCompat.getActionView(menuItem);
        txtCart = (TextView) view.findViewById(R.id.txtslcart);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        txtCart.setText(String.valueOf(cartPresenterLogic.getTotalListCart(this)));
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {
            txtCart.setText(String.valueOf(cartPresenterLogic.getTotalListCart(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    //not use
    @Override
    public void getListCart(List<Product> list) {

    }
}
