///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.gui.component.PdfPreviewer.previewPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class FilePreviewer {

    public static void setPreviewPanel(File selectedFile) {
        if (selectedFile != null) {
            String fileName = selectedFile.getName().toLowerCase();
            if (fileName.endsWith(".pdf")) {
                showPdfPreview(selectedFile);
            } else if (fileName.endsWith(".docx") || fileName.endsWith(".doc")) {
                showWordPreview(selectedFile);
            } else {
                JOptionPane.showMessageDialog(null, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showPdfPreview(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int numPages = document.getNumberOfPages();
            JPanel pagesPanel = new JPanel(new GridLayout(0, 1));
            for (int i = 0; i < numPages; i++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 80);
                ImageIcon imageIcon = new ImageIcon(bim);
                JLabel label = new JLabel(imageIcon);
                pagesPanel.add(label);
            }
            JScrollPane scrollPane = new JScrollPane(pagesPanel);
            previewPanel.removeAll();
            previewPanel.add(scrollPane, BorderLayout.CENTER);
            previewPanel.revalidate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load PDF file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showWordPreview(File file) {
        try (FileInputStream fis = new FileInputStream(file); XWPFDocument document = new XWPFDocument(fis); XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            String text = extractor.getText();
            JTextArea textArea = new JTextArea(text);
            textArea.setEditable(false);
            previewPanel.removeAll();
            previewPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            previewPanel.revalidate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load Word file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
