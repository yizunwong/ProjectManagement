/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.getValues;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public interface FileController {

    public void saveFile(String fileName);

    public void updateFile(String fileName,String[] content);

    class FileService {

        public JSONObject getObj() {
            return jsonObj;
        }

        public JSONArray getJSONArray() {
            return jsonArray;
        }

        private JSONObject jsonObj = new JSONObject();
        private JSONArray jsonArray = new JSONArray();
        private final JSONHandler jsonHandler = new JSONHandler();
        private String fileContent;

        public void write(Object object, String fileName, boolean append) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {

                if (object instanceof JSONObject) {
                    writer.write(object.toString() + "\n");
                    System.out.println("Data has been written to the file.");

                } else if (object instanceof JSONArray JSONArray) {

                    for (int i = 0; i < JSONArray.length(); i++) {
                        JSONObject obj = JSONArray.getJSONObject(i);
                        writer.write(obj.toString() + "\n");
                    }

                }
            } catch (IOException e) {
                System.err.println("Error writing data to file: " + e.getMessage());
            }
        }
        //write data in JSOn format to text file

        public void writeData(String fileName, String[] keys, String[] content) {
            jsonObj = jsonHandler.toJSONObject(keys, content);
            //Read the content from file and check is exists or not
            readData(fileName, "array");
            jsonArray = getJSONArray();
            boolean alreadyExists = checkExists(jsonArray, jsonObj);

            //if not exists write into file
            if (!alreadyExists) {
                write(jsonObj, fileName, true);

            } else {
                System.out.println("Data already exists");
            }
        }

        public boolean checkExists(JSONArray jsonArray, JSONObject jsonObj) {
            boolean alreadyExists = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.similar(jsonObj)) {
                    alreadyExists = true;
                    break;
                }
            }

            return alreadyExists;

        }

        //Read the data from text file and convert into JSONObject/Array
        public Object readData(String fileName, String dataType) {

            try {
                fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
//                System.out.println("file content:" + fileContent);
                if (dataType.equalsIgnoreCase("object")) {
                    return new JSONObject(fileContent);
                } else {
                    String[] lines = fileContent.split("\\r?\\n");
                    return new JSONArray(Arrays.toString(lines));
                }

            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());

            }
            return null;
        }

        public void updateData(String fileName, String[] keys, String[] content) {
            jsonArray = (JSONArray) readData(fileName, "array");
            jsonObj = jsonHandler.toJSONObject(keys, content);
            String newId = jsonObj.getString("ID");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String id = obj.getString("ID");

                if (id.equals(newId)) {
                    // Check if any value has changed
                    if (!obj.similar(jsonObj)) {
                        // Update the object
                        jsonArray.put(i, jsonObj);
                    }
                }

            }

            write(jsonArray, fileName, false);

        }

        public void deleteData(String id, String fileName) {
            jsonArray = (JSONArray) readData(fileName, "array");
            findAndRemoveById(jsonArray, id);
            write(jsonArray, fileName, false);

        }

        public void moveData(String id, String role) {
            JSONArray lecturerArray = (JSONArray) readData("lecturer.txt", "array");
            JSONArray projectManagerArray = (JSONArray) readData("project_manager.txt", "array");

            if (role.equalsIgnoreCase("project manager")) {
                JSONObject removed = findAndRemoveById(lecturerArray, id);
                projectManagerArray.put(removed);
            } else {
                JSONObject removed = findAndRemoveById(projectManagerArray, id);
                lecturerArray.put(removed);
            }

            write(lecturerArray, "lecturer.txt", false);
            write(projectManagerArray, "project_manager.txt", false);

        }

        //remove the object from array by id and return the removed object
        private JSONObject findAndRemoveById(JSONArray jsonArray, String id) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("ID").equals(id)) {
                    JSONObject removed = (JSONObject) jsonArray.remove(i);
                    return removed;
                }
            }
            return null;
        }

        public JSONArray searchData(String fileName, String valueToSearch, JSONArray dataArray ) {
            JSONArray searchedArray = new JSONArray();
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject jsonObject = dataArray.getJSONObject(i);
                if (containsValue(jsonObject, valueToSearch)) {
                    searchedArray.put(jsonObject);
                }
            }
            return searchedArray;
        }

        public boolean containsValue(JSONObject jsonObject, String valueToSearch) {
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof String && ((String) value).contains(valueToSearch)) {
                    return true;
                }
            }
            return false;
        }

        //Show the data into table
        public void showFileData(JTable table, String[] columns, String fileName, JSONArray jsonArray) {
            if (jsonArray == null) {
                // Attempt to read data from file
                jsonArray = (JSONArray) readData(fileName, "array");
                if (jsonArray == null) {
                    return;
                }
            }

            DefaultTableModel model = createTableModel(columns, jsonArray);
            if (model != null) {
                table.setModel(model);
            } else {
            }
        }

        private DefaultTableModel createTableModel(String[] columns, JSONArray jsonArray) {
            DefaultTableModel model = new DefaultTableModel();
            for (String column : columns) {
                model.addColumn(column);
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = (JSONObject) jsonArray.get(i);
                Object[] rowData = new Object[columns.length];
                for (int j = 0; j < columns.length; j++) {
                    String columnKey = columns[j];
                    rowData[j] = student.opt(columnKey);
                }
                model.addRow(rowData);
            }

            return model;
        }
    }

    public class ImageController {

        public ImageIcon getImageIcon(File selectedFile) {
            ImageIcon avatarImage = new ImageIcon(selectedFile.toString());
            java.awt.Image originalImage = avatarImage.getImage();
            java.awt.Image scaledImage = originalImage.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            return scaledIcon;
        }
    }

    public class Country {

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

    public class Course {

        private String[] courses;
        private final FileController.FileService file;

        public Course() {
            this.file = new FileController.FileService();
            this.courses = null;
        }

        public String[] getCourse(String entry_level) {

            switch (entry_level) {
                case "Foundation" ->
                    this.courses = findCourse("Foundation");
                case "Diploma" ->
                    this.courses = findCourse("Diploma");
                case "Degree" ->
                    this.courses = findCourse("Degree");
                case "Masters Degree" ->
                    this.courses = findCourse("Masters Degree");
                case "PhD" ->
                    this.courses = findCourse("PhD");
                default -> {
                    this.courses = new String[]{"-"};
                }

            }
            return courses;
        }

        public String[] findCourse(String key) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");

            //return the specific course
            List<String> programNames = getValues(jsonObj, "areas.programs." + key + ".name", true);
            return programNames.toArray(String[]::new);

        }

        public static int findIndexContainingValue(List<String> list, String value) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(value)) {
                    return i;
                }
            }
            return -1; // Value not found in any element of the list
        }

        public String[] findIntake(String course, String key) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");

            if (key == null || key.isEmpty() || ("-".equals(key))) {
                return new String[]{"-"};
            } else {
                List<String> programs = getValues(jsonObj, "programs", false);
                int index = findIndexContainingValue(programs, course);

                List<String> intake_dates = getValues(jsonObj, "intake_dates", false);
                List<String> intake_date = getValues(intake_dates, key, false);
                String dates = intake_date.get(index);
                String[] date = dates.replaceAll("[\"\\[\\]]", "").split(",");
                return date;
            }
        }

    }

}
