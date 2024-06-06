/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test extends JFrame {
    private JButton openButton;
    private JPanel previewPanel;

    public test() {
        setTitle("File Previewer");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        openButton = new JButton("Open PDF File");
        openButton.addActionListener(e -> openFile());

        previewPanel = new JPanel(new BorderLayout());
        
        setLayout(new BorderLayout());
        add(openButton, BorderLayout.NORTH);
        add(previewPanel, BorderLayout.CENTER);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().toLowerCase().endsWith(".pdf")) {
                showPdfPreview(selectedFile);
            } else {
                JOptionPane.showMessageDialog(this, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showPdfPreview(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300);
            ImageIcon imageIcon = new ImageIcon(bim);
            JLabel label = new JLabel(imageIcon);
            previewPanel.removeAll();
            previewPanel.add(new JScrollPane(label), BorderLayout.CENTER);
            previewPanel.revalidate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load PDF file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            test previewer = new test();
            previewer.setVisible(true);
        });
    }
}
