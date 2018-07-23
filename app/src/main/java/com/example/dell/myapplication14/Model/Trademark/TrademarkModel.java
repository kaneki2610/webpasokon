package com.example.dell.myapplication14.Model.Trademark;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Trademark;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TrademarkModel {

    public List<Trademark> getListTrademark(){
        List<Trademark> trademarkList=new ArrayList<>();
        List<HashMap<String,String>> hashMapList=new ArrayList<>();

        HashMap<String,String> hashMapFun=new HashMap<>();
        hashMapFun.put("fun","getListTrademark");

        hashMapList.add(hashMapFun);

        DowloadJSON dowloadJSON=new DowloadJSON(Config.API_trademark_POST,hashMapList);
        dowloadJSON.execute();

        try {
            String data=dowloadJSON.get();
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("Trademark");
            int count=jsonArray.length();
            for(int i=0;i<count;i++)
            {
                JSONObject value=jsonArray.getJSONObject(i);
                Trademark trademark=new Trademark();
                trademark.setTRADEMARK_ID(value.getInt("TRADEMARK_ID"));
                trademark.setTRADEMARK_NAME(value.getString("TRADEMARK_NAME"));
                trademark.setImage(value.getString("image"));
                trademarkList.add(trademark);
                Log.d("sp",trademarkList.get(i).getTRADEMARK_NAME());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trademarkList;

    }
}
