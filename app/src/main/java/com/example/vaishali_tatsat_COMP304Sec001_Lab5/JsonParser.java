package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {

    private HashMap<String, String>  parseJsonObject(JSONObject object){
        HashMap<String, String> dataList = new HashMap<>();
        try {
            String name = object.getString("name");
            String latitude = object.getJSONObject("geometry").getJSONObject("location").getString("lat");
            String longitude = object.getJSONObject("geometry").getJSONObject("location").getString("lng");
            String address =  object.getString("formatted_address");

            dataList.put("name", name);
            dataList.put("lat", latitude);
            dataList.put("lng", longitude);
            dataList.put("address",address);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataList;

    }

    private List<HashMap<String, String>> parseJsonArray(JSONArray jsonArray){
        List<HashMap<String, String>>  dataList = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++){
            try {
                HashMap<String, String> data  = parseJsonObject((JSONObject) jsonArray.get(i));
                dataList.add(data);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return dataList;

    }

    public List<HashMap<String, String>> parseResult(JSONObject jsonObject){
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return parseJsonArray(jsonArray);
    }
}
