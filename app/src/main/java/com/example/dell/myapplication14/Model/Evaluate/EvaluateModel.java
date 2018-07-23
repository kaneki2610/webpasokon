package com.example.dell.myapplication14.Model.Evaluate;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Evaluate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EvaluateModel {
    public boolean AddEvaluate(Evaluate evaluate) {

        Boolean result = false;
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFunction = new HashMap<>();
        hashMapFunction.put("fun", "addEvaluate");

        HashMap<String, String> hashMapEVALUATE_ID = new HashMap<>();
        hashMapEVALUATE_ID.put("EVALUATE_ID", evaluate.getEvaluate_id());

        HashMap<String, String> hashMapProductID = new HashMap<>();
        hashMapProductID.put("Product_id", String.valueOf(evaluate.getProduct_id()));

        HashMap<String, String> hashMapTitle = new HashMap<>();
        hashMapTitle.put("TITLE", evaluate.getTitle());

        HashMap<String, String> hashMapContent = new HashMap<>();
        hashMapContent.put("CONTENT", evaluate.getContent());

        HashMap<String, String> hashMapStar = new HashMap<>();
        hashMapStar.put("NUMBER_STAR", String.valueOf(evaluate.getNumber_star()));

        HashMap<String, String> hashMapDeviceName = new HashMap<>();
        hashMapDeviceName.put("DEVICE_NAME", evaluate.getDevice_name());

        hashMapList.add(hashMapFunction);
        hashMapList.add(hashMapEVALUATE_ID);
        hashMapList.add(hashMapProductID);
        hashMapList.add(hashMapTitle);
        hashMapList.add(hashMapContent);
        hashMapList.add(hashMapStar);
        hashMapList.add(hashMapDeviceName);

        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_EVALUATE, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            result = jsonObject.getBoolean("result");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
