package com.example.dell.myapplication14.DowloadJSON;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DowloadJSON extends AsyncTask<String, Void, String> {
    String link = "";
    List<HashMap<String, String>> hashMapList = new ArrayList<>();
    StringBuilder result;

    public DowloadJSON(String link) {
        this.link = link;
    }

    public DowloadJSON(String link, List<HashMap<String, String>> hashMapList) {
        this.link = link;
        this.hashMapList = hashMapList;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (hashMapList.size() != 0) {
                data = PostMethod(httpURLConnection);
            } else {
                data = GetMethod(httpURLConnection);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("test", data);
        return data;
    }

    private String GetMethod(HttpURLConnection httpURLConnection) {
        String data = "";
        try {
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            result = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                result.append(line);
                line = bufferedReader.readLine();
            }
            data = result.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String PostMethod(HttpURLConnection httpURLConnection) {
        String data = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            Uri.Builder builder = new Uri.Builder();
            int count = hashMapList.size();
            for (int i = 0; i < count; i++) {
                for (Map.Entry<String, String> values : hashMapList.get(i).entrySet()) {
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key, value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();

            data = GetMethod(httpURLConnection);


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
