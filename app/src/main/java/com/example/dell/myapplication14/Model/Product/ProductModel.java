package com.example.dell.myapplication14.Model.Product;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductModel {
    //get list of top phone and laptop
    public List<Product> getListTopPhoneAndlaptop() {

        List<Product> productList = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "getListTopPhoneAndLaptop");

        hashMapList.add(hashMapFun);

        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_trademark_POST, hashMapList);

        try {
            String data = dowloadJSON.get();
            Log.d("test", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("TOPPHONELAPTOP");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = jsonArray.getJSONObject(i);
                Product product = new Product();
                dowloadJSON.execute();
                product.setProduct_id(value.getInt("Product_id"));
                product.setProduct_name(value.getString("Product_name"));
                product.setPrice(value.getInt("Price"));
                product.setBig_image(value.getString("Image"));
                productList.add(product);
                Log.d("test", productList.get(i).getProduct_name());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productList;

    }
    //get list product --trademark
    public List<Product> getListProduct(String trademark_id, int limit) {
        List<Product> productList = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "getProduct");

        HashMap<String, String> hashMapKey = new HashMap<>();
        hashMapKey.put("trademarkID", trademark_id);

        HashMap<String, String> hashMapLimit = new HashMap<>();
        hashMapLimit.put("limit", String.valueOf(limit));

        hashMapList.add(hashMapLimit);
        hashMapList.add(hashMapFun);
        hashMapList.add(hashMapKey);


        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_trademark_POST, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("PRODUCT");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = jsonArray.getJSONObject(i);
                Product product = new Product();
                product.setProduct_id(value.getInt("Product_id"));
                product.setProduct_name(value.getString("Product_name"));
                product.setPrice(value.getInt("Price"));
                product.setBig_image(value.getString("Image"));
                productList.add(product);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
