package com.example.dell.myapplication14.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerProductDetail extends FragmentPagerAdapter {
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> namelist=new ArrayList<>();
    public ViewPagerProductDetail(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFlag(Fragment fragment,String name)
    {
        fragmentList.add(fragment);
        namelist.add(name);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return namelist.get(position);
    }
}
