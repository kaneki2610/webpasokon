package com.example.dell.myapplication14.Model.MenuJSON;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParseJsonMenu {
    public List<Category> ParseJSONMenu(String data)
    {
        List<Category>list=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("Category");
            int count=jsonArray.length();
            for(int i=0;i<count;i++)
            {
                JSONObject value=jsonArray.getJSONObject(i);
                Category category=new Category();
                category.setCategory_id(value.getInt("Category_id"));
                category.setCategory_name(value.getString("Category_name"));
                category.setParent_id(value.getInt("Parent_id"));
                list.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Category>GetMenuChild(int category_id)
    {
        List<Category>categoryList=new ArrayList<>();
        List<HashMap<String,String>>hashMapList=new ArrayList<>();
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("parent_id",String.valueOf(category_id));
        hashMapList.add(hashMap);
        DowloadJSON dowloadJSON=new DowloadJSON(Config.API_category_POST,hashMapList);
        dowloadJSON.execute();

        try {
            String data=dowloadJSON.get();
            ParseJsonMenu parseJsonMenu=new ParseJsonMenu();
            categoryList=parseJsonMenu.ParseJSONMenu(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
