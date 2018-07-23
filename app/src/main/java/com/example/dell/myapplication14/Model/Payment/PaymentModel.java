package com.example.dell.myapplication14.Model.Payment;

import android.util.Log;

import com.example.dell.myapplication14.Config.Config;
import com.example.dell.myapplication14.DowloadJSON.DowloadJSON;
import com.example.dell.myapplication14.Model.Object.Bill;
import com.example.dell.myapplication14.Model.Object.Bill_Detail;
import com.example.dell.myapplication14.Model.Object.Evaluate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PaymentModel
{
    public boolean AddBill(Bill bill) {

        Boolean result = false;
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        HashMap<String, String> hashMapFunction = new HashMap<>();
        hashMapFunction.put("fun", "addBill");

        List<Bill_Detail>billDetailList=bill.getBill_detailList();
        String json="{\"ListProduct\" : [ ";
        for(int i=0;i<billDetailList.size();i++)
        {
            json+="{";
            json+="\"Product_id\" :"+billDetailList.get(i).getProduct_id()+",";
            json+="\"QUANTITY\" :"+billDetailList.get(i).getQuantity();
            if(i==billDetailList.size()-1)
            {
                json+="}";
            }else{
                json+="},";
            }
        }
        json+="]}";
       // Log.d("chuoi",json);

        HashMap<String, String> hashMapListProduct = new HashMap<>();
        hashMapListProduct.put("ListProduct", json);

        HashMap<String, String> hashMapNAME = new HashMap<>();
        hashMapNAME.put("NAME", bill.getNAME());

        HashMap<String, String> hashMapAddress = new HashMap<>();
        hashMapAddress.put("ADDRESS", bill.getADDRESS());

        HashMap<String, String> hashMapPhone = new HashMap<>();
        hashMapPhone.put("PHONE", bill.getPHONE());

        HashMap<String, String> hashMapTransfer = new HashMap<>();
        hashMapTransfer.put("TRANSFER",String.valueOf(bill.getTRANSFER()));





        hashMapList.add(hashMapFunction);
        hashMapList.add(hashMapListProduct);
        hashMapList.add(hashMapNAME);
        hashMapList.add(hashMapAddress);
        hashMapList.add(hashMapPhone);
        hashMapList.add(hashMapTransfer);



        DowloadJSON dowloadJSON = new DowloadJSON(Config.API_BILL, hashMapList);
        dowloadJSON.execute();

        try {
            String data = dowloadJSON.get();
           // Log.d("chuoi",data);
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
