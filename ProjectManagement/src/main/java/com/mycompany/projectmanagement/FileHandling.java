/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JFileChooser;
import org.json.simple.JSONObject;

/**
 *
 * @author yizun
 */
public interface FileHandling {

    public void saveFile(String fileName);

    public void uploadFile();
}

class Files {

    private JSONObject jsonObj = new JSONObject();

    public void writeFile(String fileName, String[] keys, String[] content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {



//            BufferedReader reader = new BufferedReader(new FileReader(fileName));
//            String existingHeader = reader.readLine();
//            boolean headerExists = existingHeader != null && existingHeader.equals(String.join(" ", header));
//
//            if (!headerExists) { // Format header
//                String formattedHeader = String.join(" ", header);
//                writer.write(String.format("%s%n", formattedHeader));
//            }
//
//            // Format content
//            String formattedContent = String.join(" ", content);
//            writer.write(String.format("%s%n", formattedContent));
            for (int i = 0; i < keys.length; i++) {
                jsonObj.put(keys[i], content[i]);
            }
            writer.write(jsonObj.toJSONString() + "\n");

            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
    }
}

class People {

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String name;
    public String ic;
    public String phone;
    public String gender;
    public String country;
    public String imagePath;

    public People() {
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

}

class Student extends People implements FileHandling {

    private String parent_name;
    private String dob;
    private String email;
    private String address;
    private String entry_level;
    private String course;
    public final String[] keys = {"Name", "Parent Name", "IC",
        "Phone.no", "Gender", "Country", "Address", "Email", "Birth Date",
        "Entry Level", "Course","ImagePath"};
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
        Files files = new Files();
        files.writeFile(fileName, keys, getStudent());
    }

    @Override
    public void uploadFile() {
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

class Lecturer extends People {

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

    public Course() {
        this.course = null;
    }

    public String[] getCourse(String entry_level) {
        switch (entry_level) {
            case "Foundation":
                this.course = new String[]{"Computer Science", "Software Engineering", "Computer Engineering"};
                break;
            case "Diploma":
                this.course = new String[]{"Physics", "Chemistry", "Biology"};
                break;
            case "Degree":
                this.course = new String[]{"History", "Political Science", "Economics"};
                break;
            default:
                break;

        }
        return course;
    }
}
