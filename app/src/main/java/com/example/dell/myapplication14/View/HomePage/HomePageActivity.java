package com.example.dell.myapplication14.View.HomePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.dell.myapplication14.Adapter.ExpandAdapter;
import com.example.dell.myapplication14.Adapter.ViewPagerAdapter;
import com.example.dell.myapplication14.Model.Object.Category;
import com.example.dell.myapplication14.Presenter.MenuHomePagePresenter.MenuHomePagePresenterProcess;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.HomePage.Fragment.FragementElectronic;
import com.example.dell.myapplication14.View.HomePage.Fragment.FragmentHighlights;
import com.example.dell.myapplication14.View.HomePage.Fragment.FragmentTrademark;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements HomePageImpView{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ExpandableListView expandableListView;
    ExpandAdapter expandAdapter;
    ViewFlipper viewFlipper;
    MenuHomePagePresenterProcess menuHomePagePresenterProcess;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        AddControl();
        //process toolbar and event of drawerToggle
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        //process drawerlayout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle.syncState();
        ProcessDisplayViewPager();

        //get menu
        menuHomePagePresenterProcess = new MenuHomePagePresenterProcess(HomePageActivity.this);
        menuHomePagePresenterProcess.GetListMenu();
        ActionViewFlipper();
    }

    private void ProcessDisplayViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FragmentHighlights(), "Hightlights");
        viewPagerAdapter.addFrag(new FragmentTrademark(), "Trademark");
        viewPagerAdapter.addFrag(new FragementElectronic(), "Product");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void ActionViewFlipper() {
        ArrayList<String> list = new ArrayList<>();
        /*list.add("http://lapsaigon.com/images/banner-6.png");*/
        list.add("http://192.168.1.3:82/webpasokon/background/b1.jpg");
        list.add("http://s2.webbnc.vn/uploadv2/web/56/5604/slide/2017/02/24/03/47/1487907404_banner2.png");
        list.add("http://s2.webbnc.vn/uploadv2/web/56/5604/slide/2017/02/24/04/03/1487908354_banner1.png");
        list.add("http://192.168.1.3:82/webpasokon/background/b2.jpg");
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(list.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);  //autu play
        Animation animation_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out);
        viewFlipper.setInAnimation(animation_right);
        viewFlipper.setOutAnimation(animation_out);
    }

    private void AddControl() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.expanded_menu);
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    @Override
    public void DisplayListMenu(List<Category> list) {
        expandAdapter = new ExpandAdapter(this, list);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
