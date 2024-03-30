/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yizun
 */
public class Test implements FileController {

    public static void main(String[] args) throws ParseException, IOException {
//        FileService file = new FileService();
//        file.findIntake("course.txt");
//        JSONObject test = file.getNewObj();
//        System.out.println(test.get("degree"));
            Course course = new Course();
            course.findIntake("Foundation in Information Technology");

    }

    @Override
    public void saveFile(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
