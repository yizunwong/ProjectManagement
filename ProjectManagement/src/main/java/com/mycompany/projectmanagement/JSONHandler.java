/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import com.mycompany.projectmanagement.FileController.Assessment;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
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

    public static JSONObject toJSONObject(String[] keys, String[] content) {
        Map<String, String> userData = new LinkedHashMap<>(); // Use LinkedHashMap here

        for (int i = 0; i < keys.length; i++) {
            userData.put(keys[i], content[i]);
        }
        return new JSONObject(userData);
    }

    public static JSONArray generateModuleJSON(Assessment assessment) {
        // Create a JSONArray to hold module objects
        JSONArray modulesArray = new JSONArray();
        FileController.FileService fs = new FileController.FileService();

        // Iterate over each module name
        for (String moduleName : assessment.modules) {
            // Create a JSONObject for the module
            JSONObject moduleObject = new JSONObject();
            String assessment_id = fs.generateUniqueId("assessment", "assessment.txt", "assessment_id");

            // Populate the module object with default keys and empty values
            moduleObject.put("assessment_id", assessment_id);
            moduleObject.put("student_id", assessment.student_id);
            moduleObject.put("course_id", assessment.course_id);
            moduleObject.put("intake_date", assessment.intake_date);
            moduleObject.put("module", moduleName);
            moduleObject.put("assessment_type", "");
            moduleObject.put("supervisor", "");
            moduleObject.put("second_marker", "");
            moduleObject.put("status", "");
            moduleObject.put("due_time", "");

            // Add the module object to the array
            modulesArray.put(moduleObject);
        }

        return modulesArray;
    }

    static JSONArray replaceObjects(JSONArray newArray, JSONArray secondArray) {
        for (int i = 0; i < newArray.length(); i++) {
            JSONObject newObj = newArray.getJSONObject(i);
            String assessmentId = newObj.getString("student_id");
            for (int j = secondArray.length() - 1; j >= 0; j--) {
                JSONObject oldObj = secondArray.getJSONObject(j);
                if (oldObj.getString("student_id").equals(assessmentId)) {
                    // Remove the object if student_id matches and objects are different
                    if (!newObj.similar(oldObj)) {
                        secondArray.remove(j);
                    }
                }
            }
        }
        // Add all newObj to secondArray after removing matched objects
        for (int i = 0; i < newArray.length(); i++) {
            JSONObject newObj = newArray.getJSONObject(i);
            secondArray.put(newObj);
        }
        return secondArray;
    }

    public static JSONObject treeToJson(DefaultMutableTreeNode root) {
        JSONArray areasArray = new JSONArray();
        Enumeration<TreeNode> areaNodes = root.children();
        while (areaNodes.hasMoreElements()) {
            DefaultMutableTreeNode areaNode = (DefaultMutableTreeNode) areaNodes.nextElement();
            JSONObject areaJson = createAreaJson(areaNode);
            areasArray.put(areaJson);
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("areas", areasArray);
        FileController.FileService fs = new FileController.FileService();
        fs.write(resultJson, "course.txt", false);
        return resultJson;
    }

    private static JSONObject createAreaJson(DefaultMutableTreeNode areaNode) {
        JSONObject areaJson = new JSONObject();
        areaJson.put("area", areaNode.getUserObject().toString());
        areaJson.put("programs", createProgramsJson(areaNode));
        areaJson.put("intake_dates", createIntakeDatesJson(areaNode));
        return areaJson;
    }

    private static JSONObject createProgramsJson(DefaultMutableTreeNode areaNode) {
        JSONObject programsJson = new JSONObject();
        Enumeration<TreeNode> programTypeNodes = areaNode.children();
        while (programTypeNodes.hasMoreElements()) {
            DefaultMutableTreeNode programTypeNode = (DefaultMutableTreeNode) programTypeNodes.nextElement();
            String programType = programTypeNode.getUserObject().toString();
            programsJson.put(programType, createProgramArray(programTypeNode));
        }
        return programsJson;
    }

    private static JSONArray createProgramArray(DefaultMutableTreeNode programTypeNode) {
        JSONArray programArray = new JSONArray();
        Enumeration<TreeNode> programNodes = programTypeNode.children();
        while (programNodes.hasMoreElements()) {
            DefaultMutableTreeNode programNode = (DefaultMutableTreeNode) programNodes.nextElement();
            if ("Intake Dates".equals(programNode.getUserObject().toString())) {
                continue; // Intake Dates handled separately
            }
            programArray.put(createProgramJson(programNode));
        }
        return programArray;
    }

    private static JSONObject createProgramJson(DefaultMutableTreeNode programNode) {
        JSONObject programJson = new JSONObject();
        programJson.put("name", programNode.getUserObject().toString());
        Enumeration<TreeNode> programDetailNodes = programNode.children();
        while (programDetailNodes.hasMoreElements()) {
            DefaultMutableTreeNode detailNode = (DefaultMutableTreeNode) programDetailNodes.nextElement();
            if ("Course ID".equals(detailNode.getUserObject().toString())) {
                programJson.put("id", detailNode.getFirstChild().toString());
            } else if ("Modules".equals(detailNode.getUserObject().toString())) {
                programJson.put("modules", createModulesArray(detailNode));
            }
        }
        return programJson;
    }

    private static JSONArray createModulesArray(DefaultMutableTreeNode detailNode) {
        JSONArray modulesArray = new JSONArray();
        Enumeration<TreeNode> moduleNodes = detailNode.children();
        while (moduleNodes.hasMoreElements()) {
            DefaultMutableTreeNode moduleNode = (DefaultMutableTreeNode) moduleNodes.nextElement();
            modulesArray.put(moduleNode.getUserObject().toString());
        }
        return modulesArray;
    }

    private static JSONObject createIntakeDatesJson(DefaultMutableTreeNode areaNode) {
        JSONObject intakeDatesJson = new JSONObject();
        Enumeration<TreeNode> programTypeNodes = areaNode.children();
        while (programTypeNodes.hasMoreElements()) {
            DefaultMutableTreeNode programTypeNode = (DefaultMutableTreeNode) programTypeNodes.nextElement();
            Enumeration<TreeNode> programNodes = programTypeNode.children();
            while (programNodes.hasMoreElements()) {
                DefaultMutableTreeNode programNode = (DefaultMutableTreeNode) programNodes.nextElement();
                if ("Intake Dates".equals(programNode.getUserObject().toString())) {
                    String programType = programTypeNode.getUserObject().toString();
                    intakeDatesJson.put(programType, createIntakeDatesArray(programNode));
                }
            }
        }
        return intakeDatesJson;
    }

    private static JSONArray createIntakeDatesArray(DefaultMutableTreeNode programNode) {
        JSONArray intakeDatesArray = new JSONArray();
        Enumeration<TreeNode> dateNodes = programNode.children();
        while (dateNodes.hasMoreElements()) {
            DefaultMutableTreeNode dateNode = (DefaultMutableTreeNode) dateNodes.nextElement();
            intakeDatesArray.put(dateNode.getUserObject().toString());
        }
        return intakeDatesArray;
    }

}
