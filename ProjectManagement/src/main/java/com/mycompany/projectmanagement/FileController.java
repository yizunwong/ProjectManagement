/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.generateModuleJSON;
import static com.mycompany.projectmanagement.JSONHandler.getValues;
import static com.mycompany.projectmanagement.JSONHandler.replaceObjects;
import static com.mycompany.projectmanagement.JSONHandler.toJSONObject;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public interface FileController {

    public void saveTextFile(String fileName);

    public void updateTextFile(String fileName);

    class FileService {

        public JSONObject getObj() {
            return jsonObj;
        }

        public JSONArray getJSONArray() {
            return jsonArray;
        }

        private JSONObject jsonObj = new JSONObject();
        private JSONArray jsonArray = new JSONArray();
        private String fileContent;

        /**
         * Writes JSON objects or arrays to a text file.
         *
         * @param object The JSON object or array to write.
         * @param fileName The name of the file to write to.
         * @param append Whether to append to the file if it already exists.
         */
        public void write(Object object, String fileName, boolean append) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {

                if (object instanceof JSONObject) {
                    writer.write(object.toString() + "\n");
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

        /**
         * Check the data from text file by comparing based on a specific key.
         *
         * @param fileName JSON array read from the file.
         * @param jsonObj A JSONObject to compare.
         * @return Boolean indicating if the data exists or not.
         */
        public boolean checkExists(String fileName, JSONObject jsonObj, String keyToCheck) {
            boolean alreadyExists = false;
            jsonArray = (JSONArray) readData(fileName, "array");

            Object valueToCheck = jsonObj.get(keyToCheck);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.get(keyToCheck).equals(valueToCheck)) {
                    alreadyExists = true;
                    break;
                }
            }

            return alreadyExists;
        }

        /**
         * Read data from a JSON text file.
         *
         * @param fileName The name of the file to read.
         * @param dataType A parameter to determine whether read as array or
         * object.
         * @return The object that read form JSON text file
         */
        public Object readData(String fileName, String dataType) {
            if (fileName == null) {
                fileName = "account.txt";
            }

            try {
                fileContent = new String(Files.readAllBytes(Paths.get(fileName)));
                if (dataType.equalsIgnoreCase("object")) {
                    return new JSONObject(fileContent);
                } else {
                    String[] lines = fileContent.split("\\r?\\n");
                    return new JSONArray(Arrays.toString(lines));
                }

            } catch (IOException e) {

            }
            return null;
        }

        /**
         * Writes data to a JSON file, appending it if the file already exists.
         *
         * @param fileName The name of the file to update.
         * @param jsonObj
         * @param key The key to be compare inside the JSON object
         */
        public void updateData(String fileName, JSONObject jsonObj, String key) {
            jsonArray = (JSONArray) readData(fileName, "array");
            updateJsonArrayIfObjectChanged(jsonArray, jsonObj, key);
            write(jsonArray, fileName, false);

        }

        /**
         * Updates a JSONArray by replacing an existing JSONObject with a new
         * one if any of its values have changed.
         *
         * @param jsonArray The JSONArray containing the JSONObjects to be
         * checked.
         * @param jsonObj The new JSONObject to be compared and potentially
         * added.
         * @param key The key whose value is used to identify matching
         * JSONObjects within the JSONArray.
         */
        public void updateJsonArrayIfObjectChanged(JSONArray jsonArray, JSONObject jsonObj, String key) {
            String compareKey = jsonObj.getString(key);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String objKey = obj.getString(key);

                if (objKey.equals(compareKey)) {
                    // Check if any value has changed
                    if (!obj.similar(jsonObj)) {
                        // Update the object
                        jsonArray.put(i, jsonObj);
                    }
                }
            }
        }

        /**
         * Delete data from JSON file.
         *
         * @param fileName The name of the file to delete.
         * @param id The id that need to be move
         * @param key The key that need to be compare in the JSON object
         */
        public void deleteData(String id, String fileName, String key) {
            jsonArray = (JSONArray) readData(fileName, "array");
            findAndRemoveById(jsonArray, id, key);
            write(jsonArray, fileName, false);

        }

        /**
         * Move data from a JSON file to target file.
         *
         * @param role The name of role to decide move to which file.
         * @param id The identifier used to find JSON object in array
         * @param key The key that need to be compare in the JSON object
         */
        public void moveData(String id, String role, String key) {
            JSONArray lecturerArray = (JSONArray) readData("lecturer.txt", "array");
            JSONArray projectManagerArray = (JSONArray) readData("project_manager.txt", "array");

            if (role.equalsIgnoreCase("project manager")) {
                JSONObject removed = findAndRemoveById(lecturerArray, id, key);
                projectManagerArray.put(removed);
            } else {
                JSONObject removed = findAndRemoveById(projectManagerArray, id, key);
                lecturerArray.put(removed);
            }

            write(lecturerArray, "lecturer.txt", false);
            write(projectManagerArray, "project_manager.txt", false);

        }

        /**
         * Finds and removes a JSONObject from a JSONArray based on a specified
         * key and id value.
         *
         * @param jsonArray The JSONArray to search through.
         * @param id The identifier value to search for.
         * @param key The key that used to find the matching JSONObject.
         * @return The removed JSONObject, or an empty JSONObject if not found.
         */
        public JSONObject findAndRemoveById(JSONArray jsonArray, String id, String key) {
            JSONObject removed = new JSONObject();
            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString(key).equals(id)) {
                    // Remove the object if the key matches the given ID
                    removed = (JSONObject) jsonArray.remove(i);
                }
            }
            return removed;
        }

        /**
         * Searches for JSONObjects in a JSONArray that contain a specified
         * value and returns a new JSONArray with the matching objects.
         *
         * @param fileName The name of file to search.
         * @param valueToSearch The identifier value to search for.
         * @param dataArray The JSONArray to search through.
         * @return The JSONArray contain JSONObject that matching criteria
         */
        public JSONArray searchData(String fileName, String valueToSearch, JSONArray dataArray) {
            JSONArray searchedArray = new JSONArray();
            String searchValue = valueToSearch.toLowerCase();
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject jsonObject = dataArray.getJSONObject(i);
                if (containsValue(jsonObject, searchValue)) {
                    searchedArray.put(jsonObject);
                }
            }
            return searchedArray;
        }

        /**
         * Checks if a JSONObject contains a specified value in any of its
         * fields.
         *
         * @param jsonObject The JSONObject to search through.
         * @param valueToSearch The value to search for within the fields of the
         * JSONObject.
         * @return True if the value is found in any field of the JSONObject,
         * false otherwise.
         */
        public boolean containsValue(JSONObject jsonObject, String valueToSearch) {
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                // Check specific field if it's gender
                if ("gender".equalsIgnoreCase(key)) {
                    if (value instanceof String && ((String) value).equalsIgnoreCase(valueToSearch)) {
                        return true;
                    }
                } else {
                    // Generic check for other string fields
                    if (value instanceof String && ((String) value).toLowerCase().contains(valueToSearch)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Show the data into a JTable.
         *
         * @param table The JTable where the data should be displayed.
         * @param columns The column names to be used in the table model.
         * @param fileName The name of the file to read data from if jsonArray
         * is null.
         * @param jsonArray The JSONArray containing the data to be displayed.
         * If null, data will be read from the file.
         * @param hideLastColumns The number of last columns to hide.
         */
        //Show the data into table
        public void showFileData(JTable table, String[] columns, String fileName, JSONArray jsonArray, int hideLastColumns) {
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
                fixTable(table);
                hideColumns(table, hideLastColumns);
            }
        }

        /**
         * Creates a DefaultTableModel from a JSONArray and column names.
         *
         * @param columns The column names to be used in the table model.
         * @param jsonArray The JSONArray that needs to be displayed in the
         * table.
         * @param hideLastColumns The number of last columns to hide.
         * @return A DefaultTableModel containing the data from the JSONArray.
         */
        private DefaultTableModel createTableModel(String[] columns, JSONArray jsonArray) {
            DefaultTableModel model = new DefaultTableModel();
            // Add columns to the model
            for (String column : columns) {
                model.addColumn(column);
            }
            // Add rows to the model
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

        public void hideColumns(JTable table, int hideLastColumns) {
            int totalColumns = table.getColumnCount();
            for (int i = totalColumns - hideLastColumns; i < totalColumns; i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setMinWidth(0);
                column.setMaxWidth(0);
                column.setWidth(0);
                column.setPreferredWidth(0);
                column.setResizable(false);
            }
        }

        /**
         * Make the table fixed, disabling column reordering and resizing.
         *
         * @param table The JTable to be made fixed.
         */
        private void fixTable(JTable table) {
            // Disable column reordering
            table.getTableHeader().setReorderingAllowed(false);

            // Disable column resizing
            table.getTableHeader().setResizingAllowed(false);
            TableColumnModel columnModel = table.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                TableColumn column = columnModel.getColumn(i);
                column.setResizable(false);
            }
        }

        /**
         * Generates a unique ID for a specific data type, ensuring it does not
         * already exist in the text file.
         *
         * @param dataType The type of data for which the ID is being generated
         * (e.g., "student", "lecturer", "assessment").
         * @param fileName The name of the file containing existing data to
         * check for ID uniqueness.
         * @param key The key in the JSON objects where the IDs are stored.
         * @return A unique ID as a String.
         */
        public String generateUniqueId(String dataType, String fileName, String key) {

            String prefix;
            switch (dataType.toLowerCase()) {
                case "student" ->
                    prefix = "TP";
                case "lecturer", "project manager" ->
                    prefix = "LC";
                case "assessment" ->
                    prefix = "ASMT";
                case "report" ->
                    prefix = "RP";
                case "presentation" ->
                    prefix = "PRES";
                case "request" ->
                    prefix = "REQ";
                default -> {
                    prefix = null;
                }
            }

            Random random = new Random();
            jsonArray = (JSONArray) readData(fileName, "array");
            List<String> existed_id = getValues(jsonArray, key, false);

            int randomInRange;
            String newId;
            do {
                randomInRange = random.nextInt(9999);
                newId = prefix + randomInRange;
            } while (existed_id.contains(newId));

            existed_id.add(newId);
            return newId;
        }

        /**
         * Counts the occurrences of specific values for a given key in a JSON
         * file.
         *
         * @param filename The name of the file.
         * @param key The key whose values are to be counted.
         * @param expectedValues An array of expected values to count
         * occurrences for.
         * @param searchArray
         * @return A HashMap where the keys are the expected values and the
         * values are their respective counts.
         */
        public HashMap<String, Integer> countOccurrences(String filename, String key, String[] expectedValues, JSONArray searchArray) {
            if (searchArray == null) {
                jsonArray = (JSONArray) readData(filename, "array");
            } else {
                jsonArray = searchArray;
            }

            // Initialize the counts hashmap with default values of 0 for all expected values
            HashMap<String, Integer> counts = new HashMap<>();
            for (String value : expectedValues) {
                counts.put(value, 0);
            }

            if (jsonArray == null) {
                return counts;
            }

            // Count the occurrences of each expected value
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String value = jsonObject.optString(key);
                if (counts.containsKey(value)) {
                    counts.put(value, counts.get(value) + 1);
                }
            }

            return counts;
        }

        public static void deleteFile(String filePath) {
            File file = new File(filePath);

            if (file.exists()) {
                file.delete();
            }
        }

        public void viewFile(String file_path) {
            String filePath = file_path;
            String projectDirectory = System.getProperty("user.dir");
            File file = new File(projectDirectory + filePath);

            if (file.exists()) {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(file);
                    } else {
                        JOptionPane.showMessageDialog(null, "Desktop is not supported. Cannot open the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ImageController {

        /**
         * To scale the image into a fixed size
         *
         * @param selectedFile The name of the selected file that used to scale.
         * @return The scaled image icon that used in profile
         */
        public ImageIcon getImageIcon(File selectedFile) {
            ImageIcon avatarImage = new ImageIcon(selectedFile.toString());
            java.awt.Image originalImage = avatarImage.getImage();
            java.awt.Image scaledImage = originalImage.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            return scaledIcon;
        }
    }

    public class State {

        private final String[] states = {
            "Johor",
            "Kedah",
            "Kelantan",
            "Malacca",
            "Negeri Sembilan",
            "Pahang",
            "Penang",
            "Perak",
            "Perlis",
            "Sabah",
            "Sarawak",
            "Selangor",
            "Terengganu",
            "Kuala Lumpur",
            "Labuan",
            "Putrajaya"
        };

        /**
         * Retrieves an array of all country names based on ISO country codes.
         *
         * @return An array with country name
         */
        public String[] getStates() {
            return states;
        }

    }

    public class Area {

        /**
         * Finds name for a specific department.
         *
         * @return An array of department.
         */
        public String[] findDepartment() {
            FileController.FileService fs = new FileController.FileService();
            JSONObject jsonObj = (JSONObject) fs.readData("course.txt", "object");
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                return new String[0]; // Or any appropriate action
            }

            //return the specific course
            List<String> departmentNames = getValues(jsonObj, "areas.area", true);
            return departmentNames.toArray(String[]::new);

        }

    }

    public class Course {

        private String[] courses;
        private final FileController.FileService file;

        public Course() {
            this.file = new FileController.FileService();
            this.courses = null;
        }

        /**
         * Retrieves courses based on the entry level.
         *
         * @param entry_level The entry level (e.g., "Foundation", "Diploma",
         * "Degree", "Masters Degree", "PhD").
         * @return An array of courses corresponding to the entry level.
         */
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

        /**
         * Finds courses for a specific key.
         *
         * @param key The key representing the entry level.
         * @return An array of course names.
         */
        public String[] findCourse(String key) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");

            //return the specific course
            List<String> programNames = getValues(jsonObj, "areas.programs." + key + ".name", true);
            return programNames.toArray(String[]::new);

        }

        /**
         * Finds the course ID for a specific course within a specific key.
         *
         * @param key The key representing the entry level.
         * @param course The name of the course.
         * @return The ID of the course.
         */
        public String findCourseID(String key, String course) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");

            //return the specific course
            List<String> programs = getValues(jsonObj, "areas.programs." + key + ".name", true);
            int index = findIndexContainingValue(programs, course);
            List<String> courseID = getValues(jsonObj, "areas.programs." + key + ".id", true);
            return courseID.get(index);

        }

        /**
         * Finds the index for a specific value from the list.
         *
         * @param list The list that used to search through.
         * @param value The value for search through.
         * @return The index that match the criteria.
         */
        public static int findIndexContainingValue(List<String> list, String value) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(value)) {
                    return i;
                }
            }
            return -1; // Value not found in any element of the list
        }

        /**
         * Finds intake dates for a specific course.
         *
         * @param course The name of the course.
         * @param key The key representing specific criteria for intake dates.
         * @return An array of intake dates.
         */
        public String[] findIntake(String course, String key) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                return new String[0]; // Or any appropriate action
            }

            if (key == null | ("-".equals(key))) {
                List<String> intake = getValues(jsonObj, "areas.intake_dates", true);

                return intake.toArray(String[]::new);
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

        /**
         * Finds modules for a specific course.
         *
         * @param key The key representing specific criteria for modules.
         * @param course The name of the course.
         * @return An array of modules.
         */
        public String[] findModule(String key, String course) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                return new String[0]; // Or any appropriate action
            }
            if (key == null | ("-".equals(key))) {
                List<String> modulesNestedList = getValues(jsonObj, "modules", false);
                List<String> moduleList = new ArrayList<>();
                modulesNestedList.stream()
                        .map(module -> module.replaceAll("[\"\\[\\]]", "").split(","))
                        .flatMap(Arrays::stream)
                        .forEach(moduleList::add);
                return moduleList.toArray(String[]::new);

            } else {
                List<String> programs = getValues(jsonObj, "name", false);
                int index = findIndexContainingValue(programs, course);

                List<String> modules = getValues(jsonObj, "modules", false);
                String module = modules.get(index);
                String[] moduleArray = module.replaceAll("[\"\\[\\]]", "").split(",");
                return moduleArray;

            }
        }

        public static String generateCourseID(String entry_level, String area, int number) {
            String entryCode = switch (entry_level.toLowerCase()) {
                case "foundation" ->
                    "FDN";
                case "degree" ->
                    "DEG";
                case "phd" ->
                    "PHD";
                case "masters degree" ->
                    "MDEG";
                default ->
                    "DI";
            };

            String areaCode = switch (area.toLowerCase()) {
                case "technology" ->
                    "T";
                case "engineering" ->
                    "E";
                case "hospitality" ->
                    "H";
                case "business" ->
                    "B";
                case "creative arts" ->
                    "CA";
                default ->
                    "DI";
            };
            String formattedNumber = String.format("%03d", number);

            return areaCode + "-" + entryCode + "-" + formattedNumber;

        }

    }

    class Assessment {

        public String assessment_id;
        public String student_id;
        public String course_id;
        public String intake_date;
        public String[] modules;
        public String module;
        public String assessment_type;
        public String supervisor;
        public String second_marker;
        private final String[] keys;
        public String status;
        public String due_time;

        public Assessment() {
            this.keys = new String[]{"assessment_id", "student_id", "course_id", "intake_date",
                "module", "assessment_type", "supervisor", "second_marker", "status", "due_time"};
        }

        public void setModules(String[] modules) {
            this.modules = modules;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public void setSecondMarker(String second_marker) {
            this.second_marker = second_marker;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setDueTime(String due_time) {
            this.due_time = due_time;
        }

        public void setAssessmentID(String assessment_id) {
            this.assessment_id = assessment_id;
        }

        public void setAssessmentType(String assessment_type) {
            this.assessment_type = assessment_type;
        }

        public void setSupervisor(String supervisor) {
            this.supervisor = supervisor;
        }

        public void setIntakeDate(String intake_date) {
            this.intake_date = intake_date;
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setCourseID(String course_id) {
            this.course_id = course_id;
        }

        public void saveTextFile(String fileName, Assessment assessment) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray assessmentArray = generateModuleJSON(assessment);
            fs.write(assessmentArray, fileName, true);

        }

        public String[] getAssessment() {
            String[] assessment = {assessment_id, student_id, course_id, intake_date, module, assessment_type, supervisor, second_marker, status, due_time};
            return assessment;

        }

        /**
         * Replaces the data in a specified file with the new assessment data.
         *
         * @param fileName The name of the file to update.
         * @param assessment The assessment data to replace in the file.
         */
        public void replaceData(String fileName, Assessment assessment) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray assessmentArray = generateModuleJSON(assessment);
            JSONArray dataArray = (JSONArray) fs.readData(fileName, "array");
            JSONArray replacedArray = replaceObjects(assessmentArray, dataArray);
            fs.write(replacedArray, "assessment.txt", false);

        }

        /**
         * Updates the assessment file by modifying the entries that match the
         * module and intake date of the given assessment.
         *
         * @param assessment The assessment containing the new values to update.
         */
        public void updateFileByModule(Assessment assessment) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray jsonArray = (JSONArray) fs.readData("assessment.txt", "array");

            // Create a map with the new values for each field
            Map<String, String> newValuesMap = new HashMap<>();
            newValuesMap.put("second_marker", assessment.second_marker);
            newValuesMap.put("due_time", assessment.due_time);
            newValuesMap.put("assessment_type", assessment.assessment_type);
            newValuesMap.put("supervisor", assessment.supervisor);
            newValuesMap.put("status", assessment.status);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.getString("module").equals(assessment.module) && jsonObject.getString("intake_date").equals(assessment.intake_date)) {
                    for (String field : newValuesMap.keySet()) {
                        String newValue = newValuesMap.get(field);
                        if (newValue != null && !jsonObject.getString(field).equals(newValue)) {
                            jsonObject.put(field, newValue);
                        }
                    }
                }
            }

            fs.write(jsonArray, "assessment.txt", false);
        }

        public void updateFile(String fileName, JSONObject assessment) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, assessment, "assessment_id");
        }

    }

    class Submission {

        public void setSupervisor(String supervisor) {
            this.supervisor = supervisor;
        }

        public void setSecondMarker(String second_marker) {
            this.second_marker = second_marker;
        }

        public String getReportID() {
            return Rid;
        }

        public String Rid;

        public void setAssessmentId(String assessment_id) {
            this.assessment_id = assessment_id;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String assessment_id;
        public String supervisor;
        public String second_marker;
        public String student_id;
        public String status;
        public String mark;
        public String grade;
        public String feedback;
        public String filepath;
        public final String[] keys;

        public Submission() {
            this.keys = new String[]{"ID", "student_id", "assessment_id", "supervisor", "second_marker", "status", "mark", "grade", "feedback", "file_path"};
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setFilePath(String file_path) {
            this.filepath = file_path;
        }

        public void setReportID(String Rid) {
            this.Rid = Rid;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public JSONObject getSubmission() {
            String[] submission = {Rid, student_id, assessment_id, supervisor, second_marker, status, mark, grade, feedback, filepath,};
            JSONObject jsonObj = toJSONObject(keys, submission);
            return jsonObj;
        }

        public void saveTextFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.write(getSubmission(), fileName, true);

        }

        public void updateFile(String fileName, JSONObject submission) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, submission, "ID");
        }

    }

    class Request {

        public void setAssessmentID(String assessment_id) {
            this.assessment_id = assessment_id;
        }

        public void setSupervisor(String supervisor) {
            this.supervisor = supervisor;
        }

        public String module;
        public String assessment_id;
        public String request_date;
        public String supervisor;
        public String request_id;
        public String status;
        public String student_id;
        public String remark;
        public final String[] keys;

        public Request() {
            this.keys = new String[]{"ID", "student_id", "assessment_id", "module", "request_date", "supervisor", "status", "remark"};
        }

        public void setRequestID(String request_id) {
            this.request_id = request_id;
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setRequestDate(String request_date) {
            this.request_date = request_date;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public JSONObject getRequest() {
            String[] booking = {request_id, student_id, assessment_id, module, request_date, supervisor, status, remark};
            JSONObject jsonObj = toJSONObject(keys, booking);
            return jsonObj;
        }

        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.write(getRequest(), fileName, true);
        }

        public void updateFile(String fileName, JSONObject booking) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, booking, "ID");
        }
    }

    class Presentation {

        public String module;
        public String request_date;
        public String lecturer;
        public String request_id;
        public String status;
        public String student_id;
        public String presentation_id;
        public String remark;
        public final String[] keys;

        public Presentation() {
            this.keys = new String[]{"ID", "request_id", "student_id", "presentation_date", "assessment_id", "supervisor", "status", "remark"};
        }

        public void setPresentationID(String presentation_id) {
            this.presentation_id = presentation_id;
        }

        public void setRequestID(String request_id) {
            this.request_id = request_id;
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setRequestDate(String request_date) {
            this.request_date = request_date;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public void setLecturer(String lecturer) {
            this.lecturer = lecturer;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public JSONObject getPresentation() {
            String[] presentation = {presentation_id, request_id, student_id, request_date, module, lecturer, status, remark};
            JSONObject jsonObj = toJSONObject(keys, presentation);
            return jsonObj;
        }

        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.write(getPresentation(), fileName, true);
        }

    }

}
