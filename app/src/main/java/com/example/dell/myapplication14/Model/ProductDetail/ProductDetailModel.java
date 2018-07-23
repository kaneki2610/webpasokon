package com.example.dell.myapplication14.Model.ProductDetail;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.Model.Object.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductDetailModel {
    //get product detail
    public Product getProductDetail(int product_id) {
        Product product = new Product();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "getProductDetail");

        HashMap<String, String> hashMapKey = new HashMap<>();
        hashMapKey.put("product_id", String.valueOf(product_id));


        hashMapList.add(hashMapFun);
        hashMapList.add(hashMapKey);


        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_trademark_POST, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();
            Log.d("kt", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("Product Detail");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = jsonArray.getJSONObject(i);
                product.setProduct_id(value.getInt("Product_id"));
                product.setProduct_name(value.getString("Product_name"));
                product.setPrice(value.getInt("Price"));
                product.setSmall_image(value.getString("Image"));
                product.setDescrption(value.getString("Description"));
                product.setQuantity(value.getInt("Quantity"));
                product.setCategory_id(value.getInt("Category_id"));
                product.setTrademark_id(value.getInt("TRADEMARK_ID"));
                product.setCount_buy(value.getInt("COUNT_BUY"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }
    //get digital product
    public List<DigitalInfo> getDigitalInfo(int product_id) {
        List<DigitalInfo> list = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "getDigitalInfo");

        HashMap<String, String> hashMapKey = new HashMap<>();
        hashMapKey.put("product_id", String.valueOf(product_id));

        hashMapList.add(hashMapFun);
        hashMapList.add(hashMapKey);


        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_trademark_POST, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("DigitalInfo");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = jsonArray.getJSONObject(i);
                DigitalInfo digitalInfo = new DigitalInfo();
                digitalInfo.setName(value.getString("Name"));
                digitalInfo.setValue(value.getString("Value"));
                list.add(digitalInfo);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Evaluate> getListEvaluate(int product_id, int limit) {
        List<Evaluate> list = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "showEvaluate");

        HashMap<String, String> hashMapKey = new HashMap<>();
        hashMapKey.put("Product_id", String.valueOf(product_id));

        HashMap<String, String> hashMapLimit = new HashMap<>();
        hashMapLimit.put("limit", String.valueOf(limit));

        hashMapList.add(hashMapFun);
        hashMapList.add(hashMapKey);
        hashMapList.add(hashMapLimit);


        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_EVALUATE, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();

            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("LIST Evaluate");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = jsonArray.getJSONObject(i);
                Evaluate evaluate=new Evaluate();
                evaluate.setEvaluate_id(value.getString("EVALUATE_ID"));
                evaluate.setProduct_id(value.getInt("Product_id"));
                evaluate.setTitle(value.getString("TITLE"));
                evaluate.setDevice_name(value.getString("DEVICE_NAME"));
                evaluate.setContent(value.getString("CONTENT"));
                evaluate.setDevice_name(value.getString("DEVICE_NAME"));
                evaluate.setNumber_star(value.getInt("NUMBER_STAR"));
                evaluate.setEvaluate_date(value.getString("EVALUATION_DATE"));
                list.add(evaluate);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
