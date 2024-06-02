/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author yizun
 */
import javax.swing.*;

public class test {
    private JFrame frame;

    public test() {
        // Create the frame
        frame = new JFrame("No Title Bar Window Example");
        frame.setSize(300, 200);

        // Set undecorated property to true to remove title bar
        frame.setUndecorated(true);

        // Set close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add some content
        JLabel label = new JLabel("This is a window without a title bar!");
        frame.add(label);

        // Make the window visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new test();
            }
        });
    }
}
