/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Submission;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.gui.panel.SubmissionList.SubmissionTable;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class SubmissionPanel extends javax.swing.JPanel {

    private String module;
    private String student_id;
    private String assessment_type;
    private String supervisor;
    private String second_marker;
    private String due_date;
    private String filepath;
    private String Rid;
    private String id;
    private final UserController userController;
//    private String mark;
//    private String grade;
//    private String feedback;
    private File selectedFile;

    public SubmissionPanel() {
        initComponents();
        initializeComboBox();
        this.userController = new UserController();
    }

    private void initializeComboBox() {
        FileController.Course cr = new FileController.Course();
        String[] modules = cr.findModule(null, null);
        moduleComboBox.setModel(new DefaultComboBoxModel<>(modules));
    }

    public void getFieldData() {
//        Date due;
        module = moduleComboBox.getSelectedItem().toString();
        student_id = IDField.getText().trim();
        assessment_type = AssessmentComboBox.getSelectedItem().toString();
        supervisor = SupervisorField.getText().trim();
        second_marker = SecondMarkerField.getText().trim();
//        due = DateChooser.getDate();
//        SimpleDateFormat dateFormat = new SimpleDateFormat();
//        due_date = dateFormat.format(due);
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = date.format(dateFormatter);
        due_date = (formattedDate + "," + time.toString());
        filepath = FilePathField.getText().trim();
        Rid = RIDField.getText().trim();
    }

    public void setFieldData(Submission submission) {
        submission.setModule(module);
        submission.setStudentID(student_id);
        submission.setAssessmentType(assessment_type);
        submission.setSupervisor(supervisor);
        submission.setSecondMarker(second_marker);
        submission.setDueDate(due_date);
        submission.setFilePath(filepath);
        submission.setReportID(Rid);
        submission.setMark("-");
        submission.setGrade("-");
        submission.setFeedback("-");
    }

    public void setSubmissionData(Object[] rowData) {
        RIDField.setText(rowData[0].toString());
        IDField.setText(rowData[1].toString());
        AssessmentComboBox.setSelectedItem(rowData[2].toString());
        moduleComboBox.setSelectedItem(rowData[3].toString());
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

    }

    public void ResetField() {
        RIDField.setText("");
        IDField.setText("");
        AssessmentComboBox.setSelectedItem("Internship");
        moduleComboBox.setSelectedItem("Introduction to Programming");
        SupervisorField.setText("");
        SecondMarkerField.setText("");
        dateTimePicker1.datePicker.setDate(null); // Set date component to null
        dateTimePicker1.timePicker.setTime(null); // Set time component to null
        FilePathField.setText("");
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
        IDField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        AssessmentComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        SupervisorField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SecondMarkerField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        moduleComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        FilePathField = new javax.swing.JTextField();
        ResetBtn = new javax.swing.JButton();
        AddBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        AttachBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        RIDField = new javax.swing.JTextField();

        jLabel1.setText("Student ID :");

        jLabel2.setText("Assessment Type :");

        AssessmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internship", "Investigation Reports", "CP1", "CP2", "RMCP", "FYP" }));

        jLabel3.setText("Supervisor :");

        jLabel4.setText("Second Marker :");

        jLabel5.setText("Due Date :");

        jLabel6.setText("Module :");

        moduleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moduleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setText("File Path:");

        ResetBtn.setText("Reset");
        ResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBtnActionPerformed(evt);
            }
        });

        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setText("Delete");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        AttachBtn.setText("Attach");
        AttachBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttachBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("ID :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(SupervisorField, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(54, 54, 54)
                                    .addComponent(ResetBtn)
                                    .addGap(45, 45, 45)
                                    .addComponent(AddBtn)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UpdateBtn))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(38, 38, 38)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(moduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(FilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SecondMarkerField)
                    .addComponent(AttachBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(DeleteBtn))
                    .addComponent(AssessmentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(RIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(AssessmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(SupervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(SecondMarkerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(moduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(FilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AttachBtn))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResetBtn)
                    .addComponent(AddBtn)
                    .addComponent(UpdateBtn)
                    .addComponent(DeleteBtn))
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void moduleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleComboBoxActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        ResetField();
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        Submission submission = new Submission();
        UserController.User user = userController.new User();

        getFieldData();
        setFieldData(submission);
        submission.saveFile("Report.txt");

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(SubmissionList.SubmissionTable, SubmissionList.columns, "Report.txt", null);
    }//GEN-LAST:event_AddBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed

        Submission submission = new Submission();
        getFieldData();
        setFieldData(submission);
        submission.updateFile("Report.txt", submission.getSubmission());

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(SubmissionList.SubmissionTable, SubmissionList.columns, "Report.txt", null);

    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        FileController.FileService fs = new FileController.FileService();
        getFieldData();
        fs.deleteData(Rid, "Report.txt", "ID");
        fs.showFileData(SubmissionList.SubmissionTable, SubmissionList.columns, "Report.txt", null);
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void AttachBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttachBtnActionPerformed
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        String filename = f.getAbsolutePath();
        UserController.User user = userController.new User();
        user.setReportPath();
        FilePathField.setText(user.getReportPath().toString());
        user.saveReport(user.getSelectedFile());


    }//GEN-LAST:event_AttachBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JComboBox<String> AssessmentComboBox;
    private javax.swing.JButton AttachBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTextField FilePathField;
    private javax.swing.JTextField IDField;
    private javax.swing.JTextField RIDField;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JTextField SecondMarkerField;
    private javax.swing.JTextField SupervisorField;
    private javax.swing.JButton UpdateBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JComboBox<String> moduleComboBox;
    // End of variables declaration//GEN-END:variables
}
