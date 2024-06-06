/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.getValues;
import static com.mycompany.projectmanagement.JSONHandler.toJSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public class UserController {

    public class User implements FileController {

        public String getID() {
            return id;
        }

        public File getSelectedFile() {
            return selectedFile;
        }

        public String id;
        public String email;
        public String name;
        public String ic;
        public String phone;
        public String gender;
        public String state;
        public File fullPath;
        public static String dob;
        public String address;
        public String[] keys;
        private File selectedFile;
        public final FileController.FileService fs;

        public User() {
            this.name = "";
            this.ic = "";
            this.phone = "";
            this.gender = "";
            this.state = "";
            this.fullPath = null;
            this.fs = new FileController.FileService();

        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIc() {
            return ic;
        }

        public void setIc(String ic) {
            this.ic = ic;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public File getFullPath() {
            return fullPath;
        }

        public void setFullPath(File imagePath) {
            this.fullPath = imagePath;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            User.dob = dob;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public JSONArray seachUser(String searchValue, String fileName, JSONArray dataArray) {
            if (dataArray == null) {
                dataArray = (JSONArray) fs.readData(fileName, "array");
            }
            JSONArray searchedArray = fs.searchData(fileName, searchValue, dataArray);
            return searchedArray;
        }

        public void saveFile(File selectedFile) {
            String projectDirectory = System.getProperty("user.dir");
            if (selectedFile != null) {
                try {
                    FileOutputStream fos;
                    try (FileInputStream fis = new FileInputStream(selectedFile)) {
                        fos = new FileOutputStream(projectDirectory + fullPath);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                    fos.close();

                } catch (IOException e) {
                }
            }

        }

        public static void updateAvatarImageIcon(File filePath, JLabel avatarImageIcon) {
            ImageController imageController = new ImageController();
            File fullPath;

            // Check if the file exists at the given path
            if (filePath != null) {
                if (filePath.exists()) {
                    fullPath = filePath;
                } else {
                    // Construct the path relative to the project directory
                    String projectDirectory = System.getProperty("user.dir");
                    fullPath = new File(projectDirectory, filePath.getPath());
                }

                // Get the scaled icon and set it to the label
                ImageIcon scaledIcon = imageController.getImageIcon(fullPath);
                avatarImageIcon.setIcon(scaledIcon);
            }
        }

        public String countTotalUser() {
            JSONArray dataArray = (JSONArray) fs.readData("account.txt", "array");
            if (dataArray == null) {
                return "0";
            }
            int count = dataArray.length();
            return Integer.toString(count);
        }

        public enum FileType {
            IMAGE,
            DOCUMENT
        }

        public void setUploadPath(String uploadPath, FileType fileType) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(""));

            // Only accept photo format,word and pdf file
            fileChooser.setFileFilter(createFileFilter(fileType));
            fileChooser.setAcceptAllFileFilterUsed(false);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                this.selectedFile = fileChooser.getSelectedFile(); // Get the selected file
                this.fullPath = new File(uploadPath + selectedFile.getName());
            }
        }

        private FileFilter createFileFilter(FileType fileType) {
            return new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true; // Allow directories to be shown
                    }

                    String fileName = f.getName().toLowerCase();
                    return switch (fileType) {
                        case IMAGE ->
                            fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
                            || fileName.endsWith(".png") || fileName.endsWith(".bmp");
                        case DOCUMENT ->
                            fileName.endsWith(".pdf") || fileName.endsWith(".doc")
                            || fileName.endsWith(".docx");
                        default ->
                            false;
                    };
                }

                @Override
                public String getDescription() {
                    return switch (fileType) {
                        case IMAGE ->
                            "Image files (*.jpg, *.jpeg, *.png, *.bmp)";
                        case DOCUMENT ->
                            "Document files (*.pdf, *.doc, *.docx)";
                        default ->
                            "";
                    };
                }
            };
        }

        @Override
        public void saveTextFile(String fileName) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void updateTextFile(String fileName) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    public class Lecturer extends User {

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String department;
        public String education;

        public Lecturer() {
            this.keys = new String[]{"ID", "Name", "IC",
                "Phone.no", "Gender", "State", "Address", "Email", "Birth Date",
                "Department", "Education", "ImagePath"};
        }

        public JSONObject getLecturer() {
            String[] student = {id, name, ic, phone, gender, state, address, email, dob, department, education, fullPath.toString()};
            JSONObject jsonObj = toJSONObject(keys, student);
            return jsonObj;
        }

        @Override
        public void saveTextFile(String fileName) {
            fs.write(getLecturer(), fileName, true);
        }

        @Override
        public void updateTextFile(String fileName) {
            fs.updateData(fileName, getLecturer(), "ID");

        }

    }

    public class ProjectManager extends Lecturer {

    }

    public class Student extends User {

        private String parent_name;
        private String entry_level;
        public String intake_date;
        private String course;

        public Student() {
            this.parent_name = "";
            this.entry_level = "";
            this.course = "";
            this.keys = new String[]{"ID", "Name", "Parent Name", "IC",
                "Phone.no", "Gender", "State", "Address", "Email", "Birth Date",
                "Entry Level", "Course", "Intake Date", "ImagePath"};

        }

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
        }

        public String getEntry_level() {
            return entry_level;
        }

        public void setEntry_level(String entry_level) {
            this.entry_level = entry_level;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getIntake_date() {
            return intake_date;
        }

        public void setIntake_date(String intake_date) {
            this.intake_date = intake_date;
        }

        public JSONObject getStudent() {
            String[] student = {id, name, parent_name, ic, phone, gender, state, address, email, dob, entry_level, course, intake_date, fullPath.toString()};
            JSONObject jsonObj = toJSONObject(keys, student);
            return jsonObj;
        }

        @Override
        public void saveTextFile(String fileName) {
            fs.write(getStudent(), fileName, true);
        }

        @Override
        public void updateTextFile(String fileName) {
            fs.updateData(fileName, getStudent(), "ID");

        }
    }

    public class Account extends User {

        public String password;
        public String role;
        public final FileController.FileService file;

        public Account() {
            this.file = new FileController.FileService();
            this.keys = new String[]{"ID", "Email", "Password", "Role"};
        }

        public String getId() {
            return id;
        }

        public String setId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public JSONObject getAccount() {
            String[] students = {id, email, password, role};
            JSONObject jsonObj = toJSONObject(keys, students);
            return jsonObj;
        }

        @Override
        public void saveTextFile(String fileName) {
            fs.write(getAccount(), fileName, true);
        }

        @Override
        public void updateTextFile(String fileName) {
            fs.updateData(fileName, getAccount(), "ID");
        }

        public boolean checkConfidential(String email, String password) {
            JSONArray jsonArray = (JSONArray) file.readData("account.txt", "array");

            List<String> existed_email = getValues(jsonArray, "Email", false);
            List<String> existed_password = getValues(jsonArray, "Password", false);

            int index = existed_email.indexOf(email);

            if (index != -1) {
                if (existed_password.get(index).equals(password)) {
                    JSONArray searchedArray = seachUser(email, "account.txt", null);
                    JSONObject searchedObj = searchedArray.getJSONObject(0);
                    setRole(searchedObj.getString("Role"));
                    setId(searchedObj.getString("ID"));
                    return true;
                }
            } else {
                Admin admin = new Admin();
                if (email.equals(admin.getEmail())) {
                    if (password.equals(admin.getPassword())) {
                        setRole("admin");
                        return true;
                    }
                }
            }
            return false;
        }

        public void setAccount(String role) {

            // Handle any other cases if needed
            String result = User.dob.substring(5, 7) + User.dob.substring(8, 10);
            this.password = (id + "@" + result);
            this.email = (id + "@mail.edu.my");
            this.role = role;
        }

    }

    public class Admin extends Account {

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getName() {
            return name;
        }

        public Admin() {
            this.id = "admin";
            this.email = "admin@gmail.com";
            this.password = "Admin@1234";
            this.role = "admin";
            this.name = "yizun";
        }

    }

}
