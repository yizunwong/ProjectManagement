/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.generateModuleJSON;
import static com.mycompany.projectmanagement.JSONHandler.getValues;
import static com.mycompany.projectmanagement.JSONHandler.replaceObjects;
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

    public void updateFile(String fileName, String[] content);

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
            jsonArray = (JSONArray) readData(fileName, "array");
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

        public void updateData(String fileName, String[] contentKeys, String[] content, String key) {
            jsonArray = (JSONArray) readData(fileName, "array");
            jsonObj = jsonHandler.toJSONObject(contentKeys, content);
            updateJsonArrayIfObjectChanged(jsonArray, jsonObj, key);
            write(jsonArray, fileName, false);

        }

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

        public void deleteData(String id, String fileName, String key) {
            jsonArray = (JSONArray) readData(fileName, "array");
            findAndRemoveById(jsonArray, id, key);
            write(jsonArray, fileName, false);

        }

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

        //remove the object from array by id and return the removed object
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

        public String generateUniqueId(String dataType, String fileName, String key) {

            String prefix;
            switch (dataType.toLowerCase()) {
                case "student" ->
                    prefix = "TP";
                case "lecturer", "project manager" ->
                    prefix = "LC";
                case "assessment" ->
                    prefix = "ASMT";
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

        public HashMap<String, Integer> countOccurrences(String filename, String key, String[] expectedValues) {
            FileController.FileService fs = new FileController.FileService();
            jsonArray = (JSONArray) fs.readData(filename, "array");

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

        public String findCourseID(String key, String course) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");

            //return the specific course
            List<String> programs = getValues(jsonObj, "areas.programs." + key + ".name", true);
            int index = findIndexContainingValue(programs, course);
            List<String> courseID = getValues(jsonObj, "areas.programs." + key + ".id", true);
            return courseID.get(index);

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
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                System.out.println("Error: Unable to read JSON data from file.");
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

        public String[] findModule(String key, String course) {
            JSONObject jsonObj = (JSONObject) file.readData("course.txt", "object");
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                System.out.println("Error: Unable to read JSON data from file.");
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
                System.out.println(index);

                List<String> modules = getValues(jsonObj, "modules", false);
                String module = modules.get(index);
                String[] moduleArray = module.replaceAll("[\"\\[\\]]", "").split(",");
                return moduleArray;

            }
        }

    }

    class Department {

        public String[] findDepartment() {
            FileController.FileService fs = new FileController.FileService();
            JSONObject jsonObj = (JSONObject) fs.readData("course.txt", "object");
            if (jsonObj == null) {
                // Handle the case where jsonObj is null, such as logging an error or throwing an exception
                System.out.println("Error: Unable to read JSON data from file.");
                return new String[0]; // Or any appropriate action
            }

            //return the specific course
            List<String> departmentNames = getValues(jsonObj, "areas.area", true);
            return departmentNames.toArray(String[]::new);

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

        public void saveFile(String fileName, Assessment assessment) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray assessmentArray = generateModuleJSON(assessment);
            fs.write(assessmentArray, fileName, true);

        }

        public String[] getAssessment() {
            String[] assessment = {assessment_id, student_id, course_id, intake_date, module, assessment_type, supervisor, second_marker, status, due_time};
            return assessment;

        }

        public void replaceData(String fileName, Assessment assessment) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray assessmentArray = generateModuleJSON(assessment);
            JSONArray dataArray = (JSONArray) fs.readData(fileName, "array");
            JSONArray replacedArray = replaceObjects(assessmentArray, dataArray);
            System.out.println("replaced" + replacedArray);
            fs.write(replacedArray, "assessment.txt", false);

        }

        public void updateFile(String fileName, String[] content) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, keys, content, "assessment_id");

        }

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

    }

    class Submission {

        public String module;
        public String student_id;
        public String assessment_type;
        public String supervisor;
        public String second_marker;
        public String due_date;
        public String filepath;
        public String Rid;
        public String mark;
        public String grade;
        public String feedback;
        public final String[] keys;

        public Submission() {
            this.keys = new String[]{"ID", "student_id", "assessment_type", "supervisor", "second_marker", "due_date", "module", "file_path", "mark", "grade", "feedback"};
        }

        public void setModule(String module) {
            this.module = module;
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setAssessmentType(String assessment_type) {
            this.assessment_type = assessment_type;
        }

        public void setSupervisor(String supervisor) {
            this.supervisor = supervisor;
        }

        public void setSecondMarker(String second_marker) {
            this.second_marker = second_marker;
        }

        public void setDueDate(String due_date) {
            this.due_date = due_date;
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
        
        public String[] getSubmission() {
            String[] submission = {Rid, student_id, assessment_type, supervisor, second_marker, due_date, module, filepath, mark, grade, feedback};
            return submission;
        }

        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.writeData(fileName, keys, getSubmission());

        }

        public void updateFile(String reporttxt, String[] submission) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(reporttxt, keys, submission, "ID");
        }

    }

    class Booking {

        public String module;
        public String request_date;
        public String lecturer;
        public String request_id;
        public String status;
        public String student_id;
        public String remark;
        public final String[] keys;

        public Booking() {
            this.keys = new String[]{"Request_ID", "Student_ID", "Request_Date", "Module", "Lecturer", "Status", "Remark"};
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

        public String[] getBooking() {
            String[] booking = {request_id, student_id, request_date, module, lecturer, status, remark};
            return booking;
        }

        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            System.out.println(Arrays.toString(getBooking()));
            fs.writeData(fileName, keys, getBooking());
        }

        public void updateFile(String requesttxt, String[] booking) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(requesttxt, keys, booking, "Request_ID");
        }

    }

    class VerifyBooking {

        public String module;
        public String request_date;
        public String lecturer;
        public String request_id;
        public String status;
        public String student_id;
        public String presentation_id;
        public String remark;
        public final String[] keys;

        public VerifyBooking() {
            this.keys = new String[]{"Presentation_ID", "Request_ID", "Student_ID", "Request_Date", "Module", "Lecturer", "Status", "Remark"};
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

        public String[] getVerifyBooking() {
            String[] verifybooking = {presentation_id, request_id, student_id, request_date, module, lecturer, status, remark};
            return verifybooking;
        }

        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            System.out.println(Arrays.toString(getVerifyBooking()));
            fs.writeData(fileName, keys, getVerifyBooking());
        }

        public void updateFile(String reporttxt, String[] submission) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(reporttxt, keys, submission, "Request_ID");
        }

        public String[] getBooking() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    class Marking {

        public String module;
        public String student_id;
        public String assessment_type;
        public String supervisor;
        public String second_marker;
        public String due_date;
        public String filepath;
        public String Rid;
        public String mark;
        public String grade;
        public String feedback;
        public final String[] keys;

        public Marking() {
            this.keys = new String[]{"ID", "student_id", "assessment_type", "supervisor", "second_marker", "due_date", "module", "file_path", "mark", "grade", "feedback"};
        }

        public void setModule(String module) {
            this.module = module;
        }

        public void setStudentID(String student_id) {
            this.student_id = student_id;
        }

        public void setAssessmentType(String assessment_type) {
            this.assessment_type = assessment_type;
        }

        public void setSupervisor(String supervisor) {
            this.supervisor = supervisor;
        }

        public void setSecondMarker(String second_marker) {
            this.second_marker = second_marker;
        }

        public void setDueDate(String due_date) {
            this.due_date = due_date;
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

        public String[] getSubmission() {
            String[] submission = {Rid, student_id, assessment_type, supervisor, second_marker, due_date, module, filepath, mark, grade, feedback};
            return submission;
        }

    }

}
