package com.example.springsocial.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonDownload {
    public static ResultSet RetrieveData(String type) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/foodinfdb", "root", "SpaceMysql1!");
        Statement stmt = conn.createStatement();
        ResultSet rs;

        if(Objects.equals(type, "products")) {
            rs = stmt.executeQuery("SELECT id, product_name, month_name, price FROM products");
            return rs;
        }

        rs = stmt.executeQuery("SELECT id, month_name, InfValue FROM inflation");
        return rs;
    }
    public static void getProductsToJson() throws Exception {

        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData("products");

        while(rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
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
            //Inserting key-value pairs into the json object
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