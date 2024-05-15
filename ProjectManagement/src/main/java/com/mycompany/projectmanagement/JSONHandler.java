/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandler {

    public static List<String> getValues(Object json, String query, boolean usePath) {
        List<String> values = new ArrayList<>();
        if (usePath) {
            search(json, query.split("\\."), 0, values);
        } else {
            switch (json) {
                case JSONObject jsonObject ->
                    searchForKey(jsonObject, query, values);
                case JSONArray jsonArray ->
                    searchInArray(jsonArray, query, values);
                default -> {
                    try {
                        JSONObject jsonObj = new JSONObject(json.toString());
                        searchForKey(jsonObj, query, values);
                    } catch (JSONException e) {
                        try {
                            JSONArray jsonArray = new JSONArray(json.toString());
                            searchInArray(jsonArray, query, values);
                        } catch (JSONException ex) {
                            System.out.println("Error" + ex);
                        }
                    }
                }
            }
        }
        return values;
    }

    private static void search(Object json, String[] pathParts, int index, List<String> values) {
        switch (json) {
            case JSONObject jsonObject -> {
                Object value = jsonObject.opt(pathParts[index]);
                if (value != null) {
                    if (index == pathParts.length - 1) {
                        addValue(value, values);
                    } else {
                        search(value, pathParts, index + 1, values);
                    }
                }
            }
            case JSONArray jsonArray -> {
                for (int i = 0; i < jsonArray.length(); i++) {
                    search(jsonArray.get(i), pathParts, index, values);
                }
            }
            default -> {
            }
        }
    }

    private static void addValue(Object value, List<String> values) {
        switch (value) {
            case JSONArray array -> {
                for (int i = 0; i < array.length(); i++) {
                    addValue(array.get(i), values);
                }
            }
            case JSONObject obj ->
                obj.keySet().forEach(key -> addValue(obj.opt(key), values));
            default ->
                values.add(value.toString());
        }
    }

    private static void searchForKey(JSONObject jsonObject, String key, List<String> values) {
        for (String k : jsonObject.keySet()) {
            Object value = jsonObject.get(k);
            if (k.equals(key)) {
                values.add(value.toString());
            } else if (value instanceof JSONObject jSONObject) {
                searchForKey(jSONObject, key, values);
            } else if (value instanceof JSONArray jsonArray) {
                searchInArray(jsonArray, key, values);
            }
        }
    }

    private static void searchInArray(JSONArray jsonArray, String key, List<String> values) {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object arrayValue = jsonArray.get(i);
            if (arrayValue instanceof JSONObject jSONObject) {
                searchForKey(jSONObject, key, values);
            } else if (arrayValue instanceof JSONArray jSONArray) {
                searchInArray(jSONArray, key, values);
            } else if (key.equals("") && arrayValue instanceof String) {
                values.add(arrayValue.toString());
            }
        }
    }

    public JSONObject toJSONObject(String[] keys, String[] content) {
        Map<String, String> userData = new LinkedHashMap<>(); // Use LinkedHashMap here

        for (int i = 0; i < keys.length; i++) {
            userData.put(keys[i], content[i]);
        }
        return new JSONObject(userData);
    }
}
