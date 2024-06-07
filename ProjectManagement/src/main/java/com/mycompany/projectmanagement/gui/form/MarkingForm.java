/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.form;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Assessment;
import com.mycompany.projectmanagement.FileController.Submission;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.panel.MarkingPanel;
import com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel;
import static com.mycompany.projectmanagement.Validator.validateString;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class MarkingForm extends javax.swing.JPanel {

    private String student_id, assessment_id, filepath, report_id, status, mark, grade, feedback, name;
    private final UserController userController;
    Submission submission = new Submission();
    private final FileController.FileService fs;
    private JSONArray reportArray;
    private String supervisor;
    private String second_marker;

    public MarkingForm() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();
    }

    public void setUser(String name) {
        this.name = name;

    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        reportArray = user.seachUser(name, "report.txt",null);
        fs.showFileData(MarkingPanel.reportTable, ReportSubmissionPanel.columns, "report.txt", reportArray, 0);
    }

    public void getFieldData() {
        mark = markLabel.getText().trim();
        grade = GradeComboBox.getSelectedItem().toString();
        feedback = FeedbackField.getText().trim();
    }

    public void setFieldData() {
        submission.setFilePath(filepath);
        submission.setReportID(report_id);
        submission.setAssessmentId(assessment_id);
        submission.setStudentID(student_id);
        submission.setStatus(status);
        submission.setMark(mark);
        submission.setGrade(grade);
        submission.setFeedback(feedback);
    }

    public void setReportData(Object[] rowData) {
        this.report_id = rowData[0].toString();
        reportIDField.setText(report_id);
        student_id = rowData[1].toString();
        assessment_id = rowData[2].toString();
        supervisor = rowData[3].toString();
        second_marker = rowData[4].toString();
        status = rowData[5].toString();
        mark = rowData[6].toString();
        grade = rowData[7].toString();
        feedback = rowData[8].toString();
        filepath = rowData[9].toString();
    }

    private void resetField() {
        reportIDField.setText("");
        markSlider.setValue(50);
        markLabel.setText("so");
        FeedbackField.setText("");
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
        reportIDField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        FeedbackField = new javax.swing.JTextField();
        GradeComboBox = new javax.swing.JComboBox<>();
        ViewBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        markSlider = new javax.swing.JSlider();
        markLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("ID :");

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

        UpdateBtn.setText("Mark");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        markSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                markSliderStateChanged(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(markSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(markLabel))
                                    .addComponent(GradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FeedbackField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(reportIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(ViewBtn)
                        .addGap(18, 18, 18)
                        .addComponent(UpdateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(reportIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(markSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(markLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(GradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FeedbackField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewBtn)
                    .addComponent(UpdateBtn)
                    .addComponent(jButton1))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewBtnActionPerformed
        fs.viewFile(filepath);
    }//GEN-LAST:event_ViewBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        // TODO add your handling code here:
        getFieldData();
        setFieldData();
        UserController.User user = userController.new User();
        List<String> errors = new ArrayList<>();
        validateString(report_id, "Report", errors);
        validateString(mark, "Mark", errors);

        if (errors.isEmpty()) {
            JSONArray searchedArray = user.seachUser(report_id, "report.txt",null);
            JSONObject searchObj = searchedArray.getJSONObject(0);
            if (!name.equalsIgnoreCase(second_marker)) {
                if (!searchObj.getString("status").equalsIgnoreCase("Marked")) {
                    searchObj.put("status", "Marked");
                    searchObj.put("mark", mark);
                    searchObj.put("grade", grade);
                    searchObj.put("feedback", feedback);
                    submission.updateFile("report.txt", searchObj);

                    Assessment assessment = new Assessment();
                    JSONArray assessmentArray = user.seachUser(assessment_id, "assessment.txt",null);
                    JSONObject assessmentObj = assessmentArray.getJSONObject(0);
                    assessmentObj.put("status", "Completed");
                    assessment.updateFile("assessment.txt", assessmentObj);
                } else {
                    JOptionPane.showMessageDialog(null, "Report has been marked", "Marking Error", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                searchObj.put("status", "Second Marked");
                searchObj.put("mark", mark);
                searchObj.put("grade", grade);
                searchObj.put("feedback", feedback);
                submission.updateFile("report.txt", searchObj);

                Assessment assessment = new Assessment();
                JSONArray assessmentArray = user.seachUser(assessment_id, "assessment.txt", null);
                JSONObject assessmentObj = assessmentArray.getJSONObject(0);
                assessmentObj.put("status", "Completed");
                assessment.updateFile("assessment.txt", assessmentObj);
            }
        } else {
            JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

        }
        
        refreshTable();


    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void markSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_markSliderStateChanged
        // TODO add your handling code here:
        int value = markSlider.getValue();
        markLabel.setText(Integer.toString(value));
        int gradeIndex;

        if (value >= 90) {
            gradeIndex = 0; // A+ (Distinction)
        } else if (value >= 80) {
            gradeIndex = 1; // A (Distinction)
        } else if (value >= 70) {
            gradeIndex = 2; // B+ (Credit)
        } else if (value >= 60) {
            gradeIndex = 3; // B (Credit)
        } else if (value >= 50) {
            gradeIndex = 4; // C (Pass)
        } else if (value >= 40) {
            gradeIndex = 5; // D (Pass)
        } else if (value >= 30) {
            gradeIndex = 6; // F+ (Marginal Fail)
        } else {
            gradeIndex = 7; // F (Fail)
        }

        GradeComboBox.setSelectedIndex(gradeIndex);


    }//GEN-LAST:event_markSliderStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        resetField();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FeedbackField;
    private javax.swing.JComboBox<String> GradeComboBox;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JButton ViewBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel markLabel;
    private javax.swing.JSlider markSlider;
    private javax.swing.JTextField reportIDField;
    // End of variables declaration//GEN-END:variables
}
