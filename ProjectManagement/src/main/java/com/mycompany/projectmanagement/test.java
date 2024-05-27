/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author yizun
 */
public class test {

    public void main(String[] args) {
        viewFile();
    }

    private void viewFile() {
        String filePath = "C:\\Users\\yizun\\OneDrive\\Documents\\NetBeansProjects\\Respository\\ProjectManagement\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\images.jpg";
        File file = new File(filePath);

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
