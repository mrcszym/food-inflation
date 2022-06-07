package com.example.springsocial.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class ReadJson {
    @SuppressWarnings("unchecked")
    public static void readJson() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("ceny_parowki.json"));

            JSONObject jsonObject = (JSONObject) obj;

            System.out.println(jsonObject);

            JSONArray productsList = (JSONArray) jsonObject.get("Products data");

            System.out.println(productsList);
            Iterator<JSONObject> iterator = productsList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
