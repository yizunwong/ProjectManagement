/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yizun
 */
public interface FileController {

    public void saveFile(String fileName);

    class FileService {

        public JSONObject getNewObj() {
            return newObj;
        }

        private final JSONObject jsonObj;
        private JSONObject newObj;
        private final JSONParser parser = new JSONParser();
        private final JSONArray jsonArray = new JSONArray();

        public FileService() {
            this.jsonObj = new JSONObject();
        }

        //write data in JSOn format to text file
        public void writeFile(String fileName, String[] keys, String[] content) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                Map<String, String> userData = new LinkedHashMap<>(); // Use LinkedHashMap here

                for (int i = 0; i < keys.length; i++) {
                    userData.put(keys[i], content[i]);
                }
                System.out.println(userData);

                jsonObj.putAll(userData);
                writer.write(jsonObj.toJSONString() + "\n");

                System.out.println("Data has been written to the file.");
            } catch (IOException e) {
                System.err.println("Error writing data to file: " + e.getMessage());
            }
        }

        //Read the data from text file and convert into JSONObject/Array
        public JSONArray readFile(String fileName) {

            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
                System.out.println(fileContent);
                if ("course.txt".equalsIgnoreCase(fileName)) {
                    this.newObj = (JSONObject) parser.parse(fileContent);
                } else {
                    String[] lines = fileContent.split("\\r?\\n");
                    for (String line : lines) {
                        JSONObject json = (JSONObject) parser.parse(line);
                        jsonArray.add(json);
                    }
                }
//                System.out.println("JsonArray" + jsonArray);
                System.out.println("JsonObj:" + newObj);

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return jsonArray;
        }

        //Show the data into table
        public void showFileData(JTable jTable1, String[] columns, String fileName) {
            FileController.FileService file = new FileController.FileService();
            JSONArray dataArray = file.readFile(fileName);
            DefaultTableModel model = new DefaultTableModel();

            for (String column : columns) {
                model.addColumn(column);

            }

            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject student = (JSONObject) dataArray.get(i);
                Object[] rowData = new Object[columns.length];
                for (int j = 0; j < columns.length; j++) {
                    String columnKey = columns[j];
                    rowData[j] = student.get(columnKey);
                }
                model.addRow(rowData);
            }

            jTable1.setModel(model);

        }
    }
}

class ImageController {

    public ImageIcon getImageIcon(File selectedFile) {
        ImageIcon avatarImage = new ImageIcon(selectedFile.toString());
        java.awt.Image originalImage = avatarImage.getImage();
        java.awt.Image scaledImage = originalImage.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}

class Country {

    public String[] getAllCountries() {
        String[] countries = new String[Locale.getISOCountries().length];
        String[] countryCodes = Locale.getISOCountries();
        for (int i = 0; i < countryCodes.length; i++) {
            Locale obj = new Locale("", countryCodes[i]);
            countries[i] = obj.getDisplayCountry();
        }
        return countries;
    }
}

class EntryLevel {

}

class Course extends EntryLevel {

    private String[] course;
    private String[] stringArray;
    private JSONArray intakeArray;

    public Course() {
        this.course = null;
    }

    public String[] getCourse(String entry_level) {

        switch (entry_level) {
            case "Foundation" ->
                this.course = findCourse("Foundation");
            case "Diploma" ->
                this.course = findCourse("Diploma");
            case "Degree" ->
                this.course = findCourse("Degree");
            case "Masters Degree" ->
                this.course = findCourse("Masters_Degree");
            case "PhD" ->
                this.course = findCourse("PhD");
            default -> {

                List<String> combinedList = new ArrayList<>();
                combinedList.addAll(Arrays.asList(findCourse("Foundation")));
                combinedList.addAll(Arrays.asList(findCourse("Diploma")));
                combinedList.addAll(Arrays.asList(findCourse("Degree")));
                combinedList.addAll(Arrays.asList(findCourse("Masters_Degree")));
                combinedList.addAll(Arrays.asList(findCourse("PhD")));

                this.course = combinedList.toArray(String[]::new);

            }

        }
        return course;
    }

    public String[] findCourse(String key) {
        FileController.FileService file = new FileController.FileService();
        file.readFile("course.txt");
        JSONObject jsonObj = file.getNewObj();

        JSONArray areasArray = (JSONArray) jsonObj.get("areas");
        JSONArray allSubjectsArray = new JSONArray();

// Iterate through the areas array to collect all subjects
        for (Object obj : areasArray) {
            JSONObject areaObj = (JSONObject) obj;
            JSONObject programsObj = (JSONObject) areaObj.get("programs");

            // Iterate through the programs object to collect diploma programs
            JSONArray entryArray = (JSONArray) programsObj.get(key);
            for (Object programObj : entryArray) {
                JSONObject program = (JSONObject) programObj;
                allSubjectsArray.add(program.get("name"));
            }
        }

// Convert JSONArray to String array
        stringArray = new String[allSubjectsArray.size()];
        for (int i = 0; i < allSubjectsArray.size(); i++) {
            stringArray[i] = (String) allSubjectsArray.get(i);
        }

        return stringArray;

    }

    public String[] findIntake(String course) {
        FileController.FileService file = new FileController.FileService();
        file.readFile("course.txt");
        JSONObject jsonObj = file.getNewObj();

        JSONArray areasArray = (JSONArray) jsonObj.get("areas");
        for (Object obj : areasArray) {
            JSONObject areaObj = (JSONObject) obj;
            JSONObject programsObj = (JSONObject) areaObj.get("programs");

            for (Object key : programsObj.keySet()) {
                JSONArray entryArray = (JSONArray) programsObj.get(key);
                for (Object programObj : entryArray) {
                    JSONObject program = (JSONObject) programObj;
                    if (course.equalsIgnoreCase(program.get("name").toString())) {
                        JSONObject intakeObj = (JSONObject) areaObj.get("intake_dates");
                        System.out.println(key);
                        this.intakeArray = (JSONArray) intakeObj.get(key);
                        System.out.println(intakeArray);
                        break;
                    }
                }
            }
        }
//        System.out.println(intakeObj.getClass());
//
        stringArray = new String[intakeArray.size()];
        for (int i = 0; i < intakeArray.size(); i++) {
            stringArray[i] = (String) intakeArray.get(i);
        }

        return stringArray;
    }
}
