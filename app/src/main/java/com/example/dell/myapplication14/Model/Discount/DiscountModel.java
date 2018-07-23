package com.example.dell.myapplication14.Model.Discount;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Discount;
import com.example.dell.myapplication14.Model.Object.Discount_detail;
import com.example.dell.myapplication14.Model.Object.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DiscountModel {
    public List<Discount> getListDicount() {

        List<Discount> discounts = new ArrayList<>();
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFun = new HashMap<>();
        hashMapFun.put("fun", "getListDiscount");

        hashMapList.add(hashMapFun);

        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_DISCOUNT, hashMapList);
        dowloadJSON.execute();
        try {
            String data = dowloadJSON.get();
            //Log.d("kt",data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("ListDiscount");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject_KM = jsonArray.getJSONObject(i);
                Discount discount = new Discount();
                discount.setDiscount_id(jsonObject_KM.getInt("Discount_id"));
                discount.setIMAGE(Config.SERVER + jsonObject_KM.getString("IMAGE"));
                discount.setNAME(jsonObject_KM.getString("NAME"));
                discount.setCategory_name(jsonObject_KM.getString("Category_name"));

                List<Product>productList=new ArrayList<>();
                JSONArray jsonArrayProduct = jsonObject_KM.getJSONArray("ListProduct");
                int count_p = jsonArrayProduct.length();
                for (int j = 0; j < count_p; j++) {
                    JSONObject object_P = jsonArrayProduct.getJSONObject(j);
                    Product product = new Product();
                    product.setProduct_id(object_P.getInt("Product_id"));
                    product.setPrice(object_P.getInt("Price"));
                    product.setProduct_name(object_P.getString("Product_name"));
                    product.setBig_image(Config.SERVER + object_P.getString("Big_Image"));
                    product.setSmall_image(Config.SERVER + object_P.getString("Small_Image"));

                    Discount_detail discount_detail = new Discount_detail();
                    discount_detail.setPercent(object_P.getInt("Percent"));

                    product.setDiscount_detail(discount_detail);
                    productList.add(product);

                }
                discount.setProductList(productList);
                discounts.add(discount);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return discounts;

    }
}
