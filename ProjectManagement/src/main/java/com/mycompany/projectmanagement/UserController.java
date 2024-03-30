/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author yizun
 */
public class UserController {

}

class User {

    public String name;
    public String ic;
    public String phone;
    public String gender;
    public String country;
    public String imagePath;

    public User() {
        this.name = "";
        this.ic = "";
        this.phone = "";
        this.gender = "";
        this.country = "";
        this.imagePath = null;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}


class Student extends User implements FileController {

    private String parent_name;
    private String dob;
    private String email;
    private String address;
    private String entry_level;
    private String course;
    public final String[] keys = {"Name", "Parent Name", "IC",
        "Phone.no", "Gender", "Country", "Address", "Email", "Birth Date",
        "Entry Level", "Course", "ImagePath"};
    private File selectedFile;

    public Student() {
        this.parent_name = "";
        this.entry_level = "";
        this.course = "";

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String[] getStudent() {
        String[] student = {name, parent_name, ic, phone, gender, country, address, email, dob, entry_level, course, imagePath};
        return student;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    @Override
    public void saveFile(String fileName) {
        FileService fs = new FileService();
        fs.writeFile(fileName, keys, getStudent());
    }

    public void setUploadPath() {
        JFileChooser fileChooser = new JFileChooser();
        String projectDirectory = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(""));
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile(); // Get the selected file
            String savePath = (projectDirectory + "\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\" + selectedFile.getName());
            this.imagePath = savePath;
            System.out.println(selectedFile);
            System.out.println(imagePath);

        }
    }

    public void saveImage(File selectedFile) {
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

    }

}
