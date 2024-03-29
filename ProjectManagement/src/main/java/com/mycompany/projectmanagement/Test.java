/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import org.json.simple.parser.ParseException;


/**
 *
 * @author yizun
 */
public class Test implements FileController {

    public static void main(String[] args) throws ParseException {
        Files file = new Files();
        file.readFile();

    }

    @Override
    public void saveFile(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
