/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.getValues;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import org.json.JSONArray;
/**
 *
 * @author yizun
 */
public class UserController {

    public class User implements FileController {

        public File getSelectedFile() {
            return selectedFile;
        }

        public String id;
        public String email;
        public String name;
        public String ic;
        public String phone;
        public String gender;
        public String country;
        public File imagePath;
        public static String dob;
        public String address;
        public String[] keys;
        private File selectedFile;

        public User() {
            this.name = "";
            this.ic = "";
            this.phone = "";
            this.gender = "";
            this.country = "";
            this.imagePath = null;

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

        public File getImagePath() {
            return imagePath;
        }

        public void setImagePath(File imagePath) {
            this.imagePath = imagePath;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public JSONArray seachUser(String searchValue, String fileName) {
            FileController.FileService fs = new FileController.FileService();
            JSONArray dataArray = (JSONArray) fs.readData(fileName, "array");
            JSONArray searchedArray = fs.searchData(fileName, searchValue, dataArray);
            return searchedArray;
        }

        public void saveImage(File selectedFile) {
            System.out.println(selectedFile);
            System.out.println(imagePath);
            String projectDirectory = System.getProperty("user.dir");

            if (selectedFile != null) {
                if (!imagePath.equals(new File(projectDirectory + "\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg"))) {
                    try {
                        FileOutputStream fos;
                        try (FileInputStream fis = new FileInputStream(selectedFile)) {
                            fos = new FileOutputStream(imagePath);
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = fis.read(buffer)) != -1) {
                                fos.write(buffer, 0, bytesRead);
                            }
                        }
                        fos.close();
                        System.out.println(imagePath);

                    } catch (IOException e) {
                    }
                } else {

                }
            } else {
            }
        }

        public void setUploadPath() {
            JFileChooser fileChooser = new JFileChooser();
            String projectDirectory = System.getProperty("user.dir");
            fileChooser.setCurrentDirectory(new File(""));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                this.selectedFile = fileChooser.getSelectedFile(); // Get the selected file
                this.imagePath = new File(projectDirectory + "\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\" + selectedFile.getName());
                System.out.println(selectedFile);
            }
        }

//        public String findID(String fileName) {
//            FileService fs =  new FileService();
//            fs.readData(fileName, "array");
//            JSONArray jsonArray = fs.getJSONArray();
//            List<String> programNames = getValues(jsonArray, "id", true);
//            System.out.println(programNames);
//            return null;
//        }
        @Override
        public void saveFile(String fileName) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void updateFile(String fileName, String[] content) {
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
                "Phone.no", "Gender", "Country", "Address", "Email", "Birth Date",
                "Department", "Education", "ImagePath"};
        }

        public String[] getLecturer() {
            String[] student = {id, name, ic, phone, gender, country, address, email, dob, department, education, imagePath.toString()};
            return student;
        }

        @Override
        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.writeData(fileName, keys, getLecturer());
        }

        @Override
        public void updateFile(String fileName, String[] content) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, keys, content,"ID");

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
                "Phone.no", "Gender", "Country", "Address", "Email", "Birth Date",
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

        public String[] getStudent() {
            String[] student = {id, name, parent_name, ic, phone, gender, country, address, email, dob, entry_level, course, intake_date, imagePath.toString()};
            return student;
        }

        @Override
        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.writeData(fileName, keys, getStudent());
        }

        @Override
        public void updateFile(String fileName, String[] content) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, keys, content,"ID");

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

        public String[] getAccount() {
            String[] students = {id, email, password, role};
            return students;
        }

        @Override
        public void saveFile(String fileName) {
            FileController.FileService fs = new FileController.FileService();
            fs.writeData(fileName, keys, getAccount());
        }

        @Override
        public void updateFile(String fileName, String[] content) {
            FileController.FileService fs = new FileController.FileService();
            fs.updateData(fileName, keys, content,"ID");
        }

        public boolean checkConfidential(String email, String password) {
            JSONArray jsonArray = (JSONArray) file.readData("account.txt", "array");

            List<String> existed_email = getValues(jsonArray, "email", false);
            List<String> existed_password = getValues(jsonArray, "password", false);

            int index = existed_email.indexOf(email);

            if (index != -1) {
                if (existed_password.get(index).equals(password)) {
                    setRole("student");
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

        public String generateUniqueId(String role) {

            String prefix;
            switch (role.toLowerCase()) {
                case "student" ->
                    prefix = "TP";
                case "lecturer", "project manager" ->
                    prefix = "LC";
                default -> {
                    prefix = null;
                }
            }

            Random random = new Random();
            file.readData("account.txt", "array");
            JSONArray jsonArray = file.getJSONArray();
            List<String> existed_id = getValues(jsonArray, "ID", false);

            int randomInRange;
            String newId;
            do {
                randomInRange = random.nextInt(9999);
                newId = prefix + randomInRange;
            } while (existed_id.contains(newId));

            existed_id.add(newId);
            return newId;
        }

    }

    class Admin extends Account {

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public Admin() {
            this.id = "admin";
            this.email = "admin@gmail.com";
            this.password = "admin1234";
            this.role = "admin";
        }

    }

}
