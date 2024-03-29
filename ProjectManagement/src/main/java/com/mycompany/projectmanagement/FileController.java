/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;
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

    class Files {

        private final JSONObject jsonObj;

        public Files() {
            this.jsonObj = new JSONObject();
        }

        public void writeFile(String fileName, String[] keys, String[] content) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                Map<String, String> userData = new HashMap<>();

                for (int i = 0; i < keys.length; i++) {
                    userData.put(keys[i], content[i]);
                }
                jsonObj.putAll(userData);
                writer.write(jsonObj.toJSONString() + "\n");

                System.out.println("Data has been written to the file.");
            } catch (IOException e) {
                System.err.println("Error writing data to file: " + e.getMessage());
            }
        }

        public void readFile() throws ParseException {
            try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = new JSONArray();

                String line;
                while ((line = br.readLine()) != null) {
                    JSONObject json = (JSONObject) parser.parse(line);
                    jsonArray.add(json);

                }

                JSONObject firstObject = (JSONObject) jsonArray.get(1);

                System.out.print(firstObject.get("Name"));
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

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
