/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.form;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Assessment;
import static com.mycompany.projectmanagement.FileController.FileService.deleteFile;
import com.mycompany.projectmanagement.FileController.Submission;
import static com.mycompany.projectmanagement.FilePreviewer.setPreviewPanel;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.Validator.validateString;
import com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel;
import static com.mycompany.projectmanagement.gui.component.PdfPreviewer.previewPanel;
import static com.mycompany.projectmanagement.gui.component.PdfPreviewer.user;
import static com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel.assessmentTable;
import static com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel.reportTable;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportSubmissionForm extends javax.swing.JPanel {

    private String student_id, assessment_id, filepath, report_id, status, mark, grade, feedback, file_path, assessment_type, id;
    public final static String[] columns = {"assessment_id", "student_id", "module", "assessment_type",
        "supervisor", "second_marker", "status", "due_time"};
    private final UserController userController;
    private final FileController.FileService fs;
    String projectDirectory = System.getProperty("user.dir");
    private final Submission submission = new Submission();
    Assessment assessment = new Assessment();
    private JSONArray reportArray, assessmentArray;
    private String supervsior;
    private String second_marker;
    private String assessment_status;

//    private String mark;
//    private String grade;
//    private String feedback;
    public ReportSubmissionForm() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();

    }

    public void setUser(String id) {
        this.id = id;

    }

    public void setReportData(Object[] rowData) {
        this.report_id = rowData[0].toString();
        student_id = rowData[1].toString();
        assessment_id = rowData[2].toString();
        supervsior = rowData[3].toString();
        second_marker = rowData[4].toString();
        status = rowData[5].toString();
        mark = rowData[6].toString();
        grade = rowData[7].toString();
        feedback = rowData[8].toString();
        file_path = rowData[9].toString();
        FilePathField.setText(file_path);
        setPreviewPanel(new File(projectDirectory + file_path));
    }

    public void setAssessmentData(Object[] rowData) {
        this.report_id = null;
        assessment_id = rowData[0].toString();
        student_id = rowData[1].toString();
        assessment_type = rowData[3].toString();
        supervsior = rowData[4].toString();
        second_marker = rowData[5].toString();

        if (!rowData[7].toString().isEmpty()) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate date = LocalDate.parse(rowData[7].toString().split(",")[0], dateFormatter);
            LocalTime time = LocalTime.parse(rowData[7].toString().split(",")[1], timeFormatter);
            LocalDateTime dueDateTime = LocalDateTime.of(date, time);

            LocalDateTime currentDateTime = LocalDateTime.now();

            if (currentDateTime.isAfter(dueDateTime)) {
                assessment_status = "Late";
            } else {
                assessment_status = "Under Review";
            }
        }

    }

    public void getFieldData() {
        filepath = FilePathField.getText().trim();
    }

    public void setFieldData() {
        submission.setFilePath(filepath);
        if (report_id == null) {
            report_id = fs.generateUniqueId("report", "report.txt", "ID");
            status = "Pending";
            mark = "-";
            grade = "-";
            feedback = "-";
        }
        submission.setReportID(report_id);
        submission.setAssessmentId(assessment_id);
        submission.setStudentID(student_id);
        submission.setStatus(status);
        submission.setMark(mark);
        submission.setGrade(grade);
        submission.setFeedback(feedback);
        submission.setSupervisor(supervsior);
        submission.setSecondMarker(second_marker);
    }

    public void ResetField() {
        FilePathField.setText("");
        report_id = null;
        previewPanel.removeAll();
        revalidate();
        repaint();
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        reportArray = user.seachUser(id, "report.txt",null);
        assessmentArray = user.seachUser(id, "assessment.txt",null);
        fs.showFileData(reportTable, ReportSubmissionPanel.columns, "report.txt", reportArray, 0);
        fs.showFileData(assessmentTable, columns, "assessment.txt", assessmentArray, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        FilePathField = new javax.swing.JTextField();
        ResetBtn = new javax.swing.JButton();
        AddBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        pdfPreviewer1 = new com.mycompany.projectmanagement.gui.component.PdfPreviewer();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(169, Short.MAX_VALUE)
                        .addComponent(ResetBtn)
                        .addGap(18, 18, 18)
                        .addComponent(AddBtn)
                        .addGap(18, 18, 18)
                        .addComponent(UpdateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteBtn)
                        .addGap(162, 162, 162))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(FilePathField))
                            .addComponent(pdfPreviewer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pdfPreviewer1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResetBtn)
                    .addComponent(AddBtn)
                    .addComponent(UpdateBtn)
                    .addComponent(DeleteBtn))
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        ResetField();
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        getFieldData();
        setFieldData();
        List<String> errors = new ArrayList<>();
        validateString(filepath, "File path", errors);
        validateString(assessment_id, "Module", errors);

        if (errors.isEmpty()) {
            boolean alreadyExists = fs.checkExists("report.txt", submission.getSubmission(), "assessment_id");
            if (!assessment_type.isEmpty()) {
                if (!alreadyExists) {
                    submission.saveTextFile("report.txt");
                    JSONArray searchedArray = user.seachUser(assessment_id, "assessment.txt",null);
                    JSONObject searchObj = searchedArray.getJSONObject(0);
                    searchObj.put("status", assessment_status);
                    assessment.updateFile("assessment.txt", searchObj);
                    user.saveFile(user.getSelectedFile());
                    refreshTable();

                } else {
                    JOptionPane.showMessageDialog(null, "Dont submit same report", "Submission Error", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Module not has any assessmnet yet", "Submission Error", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_AddBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        getFieldData();
        setFieldData();
        deleteFile(projectDirectory + file_path);
        user.saveFile(user.getSelectedFile());
        submission.updateFile("report.txt", submission.getSubmission());

        fs.showFileData(reportTable, ReportSubmissionPanel.columns, "report.txt", reportArray, 0);
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Delete submission?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            getFieldData();
            deleteFile(projectDirectory + file_path);
            fs.deleteData(assessment_id, "report.txt", "assessment_id");
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(assessment_id, "assessment.txt",null);
            JSONObject searchObj = searchedArray.getJSONObject(0);
            searchObj.put("status", "In Progress");
            assessment.updateFile("assessment.txt", searchObj);

            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Submission delete cancel");
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton DeleteBtn;
    public static javax.swing.JTextField FilePathField;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JLabel jLabel7;
    private com.mycompany.projectmanagement.gui.component.PdfPreviewer pdfPreviewer1;
    // End of variables declaration//GEN-END:variables
}
