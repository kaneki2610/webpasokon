package com.example.dell.myapplication14.Presenter.MenuHomePagePresenter;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.MenuJSON.ParseJsonMenu;
import com.example.dell.myapplication14.Model.Object.Category;
import com.example.dell.myapplication14.View.HomePage.HomePageImpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MenuHomePagePresenterProcess implements MenuHomePageImp {
    HomePageImpView homePageImpView;
    public MenuHomePagePresenterProcess(HomePageImpView homePageImpView) {
        this.homePageImpView = homePageImpView;
    }
    @Override
    public void GetListMenu() {
        List<Category> categoryList = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        //method-POST
        String link = Config.API_category_POST;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("parent_id", "0");
        hashMapList.add(hashMap);
        DowloadJSON dowloadJSON = new DowloadJSON(link, hashMapList);

        //method-GET
        /*  DowloadJSON dowloadJSON=new DowloadJSON(Config.API_category);*/
        dowloadJSON.execute();

        try {
            String result = dowloadJSON.get();
            Log.d("kt",result);
            ParseJsonMenu parseJsonMenu = new ParseJsonMenu();
            categoryList = parseJsonMenu.ParseJSONMenu(result);

            homePageImpView.DisplayListMenu(categoryList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
