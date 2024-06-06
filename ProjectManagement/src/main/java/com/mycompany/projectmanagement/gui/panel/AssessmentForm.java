/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Assessment;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.Validator.validateJSONArray;
import static com.mycompany.projectmanagement.Validator.validateString;
import static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel.assessment_columns;
import static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel.dataTable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.json.JSONArray;

/**
 *
 * @author yizun
 */
public class AssessmentForm extends javax.swing.JPanel {

    public String assessment_id, student_id, course_id, intake_date, module,
            assessment_type, supervisor, second_marker, status, due_time,
            entry_level, selectedCourse;
    private final UserController userController;
    private JSONArray searchedArray;
    private JSONArray assessmentArray;
    public final FileController.FileService fs;

    /**
     * Creates new form AssessmentPanel
     */
    public AssessmentForm() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();
        initializeComboBox();
    }

    private void initializeComboBox() {
        FileController.Course cr = new FileController.Course();
        String[] modules = cr.findModule(null, null);
        String[] intake_dates = cr.findIntake(null, null);
        intakeComboBox.setModel(new DefaultComboBoxModel<>(intake_dates));
        moduleComboBox.setModel(new DefaultComboBoxModel<>(modules));
    }

    public void setData(Object[] rowData) {
        assessment_id = rowData[0].toString();
        student_id = rowData[1].toString();
        course_id = rowData[2].toString();
        intakeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{rowData[3].toString()}));
        moduleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{rowData[4].toString()}));
        assessmentTypeComboBox.setSelectedItem(rowData[5].toString());
        supervisorField.setText(rowData[6].toString());
        secondMarkerField.setText(rowData[7].toString());
        statusComboBox.setSelectedItem(rowData[8].toString());
        if (!rowData[9].toString().isEmpty()) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate date = LocalDate.parse(rowData[9].toString().split(",")[0], dateFormatter);
            LocalTime time = LocalTime.parse(rowData[9].toString().split(",")[1], timeFormatter);

            // Set the date and time in your DateTimePicker component
            dateTimePicker1.datePicker.setDate(date);
            dateTimePicker1.timePicker.setTime(time);
        } else {

            dateTimePicker1.datePicker.setDate(null);
            dateTimePicker1.timePicker.setTime(null);
        }

    }

    public void resetField() {
        supervisorField.setText("");
        secondMarkerField.setText("");
        dateTimePicker1.datePicker.setDate(null);
        dateTimePicker1.timePicker.setTime(null);

    }

    public void getFieldData() {
        module = moduleComboBox.getSelectedItem().toString();
        intake_date = intakeComboBox.getSelectedItem().toString();
        assessment_type = assessmentTypeComboBox.getSelectedItem().toString();
        supervisor = supervisorField.getText().trim();
        second_marker = secondMarkerField.getText().trim();
        status = statusComboBox.getSelectedItem().toString();
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        if (time != null && date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = date.format(dateFormatter);
            due_time = (formattedDate + "," + time.toString());
        }

    }

    public void setFieldData(Assessment assessment) {
        assessment.setAssessmentID(assessment_id);
        assessment.setStudentID(student_id);
        assessment.setCourseID(course_id);
        assessment.setIntakeDate(intake_date);
        assessment.setAssessmentType(assessment_type);
        assessment.setModule(module);
        assessment.setSupervisor(supervisor);
        assessment.setSecondMarker(second_marker);
        assessment.setStatus(status);
        assessment.setDueTime(due_time);
    }

    public List<String> validateField() {
        List<String> errors = new ArrayList<>();
        validateString(supervisor, "Supervisor", errors);
        validateString(due_time, "Due time", errors);
        validateJSONArray(assessmentArray, "Assessment", errors);
        return errors;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        supervisorField = new javax.swing.JTextField();
        assessmentTypeComboBox = new javax.swing.JComboBox<>();
        statusComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        secondMarkerField = new javax.swing.JTextField();
        intakeComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel1 = new javax.swing.JLabel();
        bulkBtn = new javax.swing.JButton();
        moduleComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        resetBtn = new javax.swing.JButton();

        jLabel3.setText("Supervisor :");

        assessmentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internship Report", "Investigation Reports", "CP1", "CP2", "RMCP", "FYP " }));

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Progress", "Late", "Completed" }));

        jLabel4.setText("Assessment Type : ");

        jLabel5.setText("Status :");

        jLabel6.setText("Second Marker : ");

        intakeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        intakeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intakeComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setText("Module : ");

        jLabel1.setText("Due Time :");

        bulkBtn.setText("Bulk");
        bulkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulkBtnActionPerformed(evt);
            }
        });

        moduleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        moduleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Intake Date : ");

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(secondMarkerField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(intakeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(moduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(resetBtn)
                            .addGap(45, 45, 45)
                            .addComponent(bulkBtn)
                            .addGap(56, 56, 56)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(assessmentTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(74, 74, 74)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(intakeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(moduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondMarkerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(assessmentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bulkBtn)
                    .addComponent(resetBtn))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void intakeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intakeComboBoxActionPerformed
        // TODO add your handling code here:
        intake_date = intakeComboBox.getSelectedItem().toString();
        UserController.User user = userController.new User();
        searchedArray = user.seachUser(intake_date, "assessment.txt",null);

    }//GEN-LAST:event_intakeComboBoxActionPerformed

    private void bulkBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulkBtnActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Submit assessment type?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            UserController.User user = userController.new User();

            Assessment assessment = new Assessment();
            getFieldData();
            setFieldData(assessment);
            if (!supervisor.equalsIgnoreCase(second_marker)) {
                module = moduleComboBox.getSelectedItem().toString();
                searchedArray = user.seachUser(intake_date, "assessment.txt",null);
                this.assessmentArray = fs.searchData("assessment.txt", module, searchedArray);

                List<String> errors = validateField();
                if (errors.isEmpty()) {
                    assessment.updateFileByModule(assessment);
                    searchedArray = user.seachUser(intake_date, "assessment.txt",null);
                    this.assessmentArray = fs.searchData("assessment.txt", module, searchedArray);

                    fs.showFileData(dataTable, assessment_columns, "assessment.txt", assessmentArray, 0);
                } else {
                    JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Supervisor and second marker cant be same person", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Assessment type submit successfully");
        }
    }//GEN-LAST:event_bulkBtnActionPerformed

    private void moduleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleComboBoxActionPerformed
        // TODO add your handling code here:
        module = moduleComboBox.getSelectedItem().toString();
        this.assessmentArray = fs.searchData("assessment.txt", module, searchedArray);
        fs.showFileData(dataTable, assessment_columns, "assessment.txt", assessmentArray, 0);
    }//GEN-LAST:event_moduleComboBoxActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        resetField();
        initializeComboBox();
        fs.showFileData(dataTable, assessment_columns, "assessment.txt", null, 0);
    }//GEN-LAST:event_resetBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> assessmentTypeComboBox;
    private javax.swing.JButton bulkBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    public static javax.swing.JComboBox<String> intakeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JComboBox<String> moduleComboBox;
    private javax.swing.JButton resetBtn;
    public static javax.swing.JTextField secondMarkerField;
    private javax.swing.JComboBox<String> statusComboBox;
    public static javax.swing.JTextField supervisorField;
    // End of variables declaration//GEN-END:variables
}
