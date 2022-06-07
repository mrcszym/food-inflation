package com.example.springsocial.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.example.springsocial.controller.DownloadXml.getResultSet;

public class DownloadJson {
    public static ResultSet RetrieveData(String type) throws Exception {
        return getResultSet(type);
    }
    public static void getProductsToJson() throws Exception {

        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData("products");

        while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("ID", rs.getInt("id"));
            record.put("Product name", rs.getString("product_name"));
            record.put("Year-month", rs.getString("month_name"));
            record.put("Average price", rs.getString("price"));
            array.add(record);
        }
        jsonObject.put("Products data", array);
        try {
            FileWriter file = new FileWriter("C:/Users/Szymon/Desktop/food-inflation/spring-social/products.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getInflationToJson() throws Exception {

        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData("inflation");

        while(rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object:
            record.put("ID", rs.getInt("id"));
            record.put("Year-month", rs.getString("month_name"));
            record.put("Value", rs.getString("InfValue"));
            array.add(record);
        }
        jsonObject.put("Products data", array);
        try {
            FileWriter file = new FileWriter("C:/Users/Szymon/Desktop/food-inflation/spring-social/inflation.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}