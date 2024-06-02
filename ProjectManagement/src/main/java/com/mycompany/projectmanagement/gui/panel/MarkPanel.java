/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Marking;
import com.mycompany.projectmanagement.UserController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class MarkPanel extends javax.swing.JPanel {

    private String module;
    private String student_id;
    private String assessment_type;
    private String supervisor;
    private String second_marker;
    private String due_date;
    private String filepath;
    private String Rid;
    private String id;
    private String mark;
    private String grade;
    private String feedback;
    private UserController userController;

    public MarkPanel() {
        initComponents();
        initializeComboBox();
    }

    private void initializeComboBox() {
        FileController.Course cr = new FileController.Course();
        String[] modules = cr.findModule(null, null);
        ModuleComboBox.setModel(new DefaultComboBoxModel<>(modules));
        this.userController = new UserController();

    }

    public void getFieldData() {
        module = ModuleComboBox.getSelectedItem().toString();
        student_id = IDField.getText().trim();
        assessment_type = AssessmentComboBox.getSelectedItem().toString();
        supervisor = SupervisorField.getText().trim();
        second_marker = SecondMarkerField.getText().trim();
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = date.format(dateFormatter);
        due_date = (formattedDate + "," + time.toString());
        filepath = FilePathField.getText().trim();
        Rid = RIDField.getText().trim();
        mark = MarkField.getText().trim();
        grade = GradeComboBox.getSelectedItem().toString();
        feedback = FeedbackField.getText().trim();
    }

    public void setFieldData(Marking marking) {
        marking.setModule(module);
        marking.setStudentID(student_id);
        marking.setAssessmentType(assessment_type);
        marking.setSupervisor(supervisor);
        marking.setSecondMarker(second_marker);
        marking.setDueDate(due_date);
        marking.setFilePath(filepath);
        marking.setReportID(Rid);
        marking.setMark(mark);
        marking.setGrade(grade);
        marking.setFeedback(feedback);
    }

    public void setMarkingData(Object[] rowData) {
        RIDField.setText(rowData[0].toString());
        IDField.setText(rowData[1].toString());
        AssessmentComboBox.setSelectedItem(rowData[2].toString());
        ModuleComboBox.setSelectedItem(rowData[3].toString());
        SupervisorField.setText(rowData[4].toString());
        SecondMarkerField.setText(rowData[5].toString());
        String dateTimeString = rowData[6].toString();

        try {
            // Split the date and time
            String[] parts = dateTimeString.split(",");
            String dateString = parts[0];
            String timeString = parts.length > 1 ? parts[1] : "00:00:00"; // default time if not provided

            // Parse the date and time
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));

            // Set the date and time on the picker components
            dateTimePicker1.datePicker.setDate(date); // You may need to convert LocalDate to the appropriate type for your datePicker
            dateTimePicker1.timePicker.setTime(time); // You may need to convert LocalTime to the appropriate type for your timePicker
        } catch (DateTimeParseException e) {
            // Handle the error (e.g., show an error message or log the issue)
            e.printStackTrace();
        }
        FilePathField.setText(rowData[7].toString());
        MarkField.setText(rowData[8].toString());
        GradeComboBox.setSelectedItem(rowData[9].toString());
        FeedbackField.setText(rowData[10].toString());
    }

    private void viewFile() {
        String filePath = FilePathField.getText();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "Desktop is not supported. Cannot open the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        RIDField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        AssessmentComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        SupervisorField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SecondMarkerField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel7 = new javax.swing.JLabel();
        ModuleComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        FilePathField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        MarkField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        FeedbackField = new javax.swing.JTextField();
        GradeComboBox = new javax.swing.JComboBox<>();
        ViewBtn = new javax.swing.JButton();
        SubmitBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();

        jLabel1.setText("ID :");

        jLabel2.setText("Student ID :");

        jLabel3.setText("Assessment Type :");

        AssessmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internship", "Investigation Reports", "CP1", "CP2", "RMCP", "FYP" }));

        jLabel4.setText("Supervisor :");

        jLabel5.setText("Second Marker :");

        jLabel6.setText("Due  Date :");

        jLabel7.setText("Module :");

        ModuleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("File Path :");

        jLabel9.setText("Mark :");

        jLabel10.setText("Grade :");

        jLabel11.setText("Feedback :");

        GradeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+ (Distinction)", "A (Distinction)", "B+ (Credit)", "B (Credit)", "C (Pass)", "D (Pass)", "F+ (Marginal Fail)", "F (Fail)" }));

        ViewBtn.setText("View");
        ViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewBtnActionPerformed(evt);
            }
        });

        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FeedbackField, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(FilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ModuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(SupervisorField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(RIDField, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(IDField, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabel3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(jLabel5)))))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AssessmentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(MarkField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(SecondMarkerField, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(GradeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(ViewBtn)
                        .addGap(83, 83, 83)
                        .addComponent(SubmitBtn)
                        .addGap(74, 74, 74)
                        .addComponent(UpdateBtn)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(RIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(AssessmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SupervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(SecondMarkerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ModuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(MarkField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(GradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(FeedbackField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewBtn)
                    .addComponent(SubmitBtn)
                    .addComponent(UpdateBtn))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewBtnActionPerformed
        viewFile();
    }//GEN-LAST:event_ViewBtnActionPerformed

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        // TODO add your handling code here:
        Marking marking = new Marking();
        UserController.User user = userController.new User();

        getFieldData();
        setFieldData(marking);
        marking.saveFile("Marking.txt");

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(MarkList.markingTable, MarkList.columns, "Marking.txt", null, 0);
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        // TODO add your handling code here:
        Marking marking = new Marking();
        getFieldData();
        setFieldData(marking);
        marking.updateFile("Marking.txt", marking.getMarking());

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(MarkList.markingTable, MarkList.columns, "Marking.txt", null, 0);
    }//GEN-LAST:event_UpdateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AssessmentComboBox;
    private javax.swing.JTextField FeedbackField;
    private javax.swing.JTextField FilePathField;
    private javax.swing.JComboBox<String> GradeComboBox;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField MarkField;
    private javax.swing.JComboBox<String> ModuleComboBox;
    private javax.swing.JTextField RIDField;
    private javax.swing.JTextField SecondMarkerField;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JTextField SupervisorField;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JButton ViewBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
