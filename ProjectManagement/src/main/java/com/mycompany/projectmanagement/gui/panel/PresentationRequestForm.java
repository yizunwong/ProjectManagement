/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Booking;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.Validator.validateString;
import static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel.assessment_columns;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.json.JSONArray;

public class PresentationRequestForm extends javax.swing.JPanel {

    private String module, id, supervisor, request_date, request_id, status, student_id, remark, assessment_id, assessment_type;
    private final FileController.FileService fs;
    private JSONArray requestArray;
    private JSONArray assessmentArray;
    private final UserController userController;

    public PresentationRequestForm() {
        initComponents();
        this.fs = new FileController.FileService();
        this.userController = new UserController();

    }

    public void setUser(String id) {
        this.id = id;

    }

    public void setAssessmentData(Object[] rowData) {
        this.request_id = null;
        assessment_id = rowData[0].toString();
        assessment_type = rowData[5].toString();
        student_id = rowData[1].toString();
        supervisor = rowData[5].toString();
        String[] modules = new String[]{rowData[4].toString()};
        ModuleComboBox.setModel(new DefaultComboBoxModel<>(modules));
        supervisorField.setText(rowData[6].toString());

    }

    public void setFieldData(Booking booking) {
        booking.setSupervisor(supervisor);
        booking.setModule(module);
        booking.setRequestDate(request_date);
        if (request_id == null) {
            request_id = fs.generateUniqueId("request", "request.txt", "ID");
            status = "Pending";
            remark = "";

        }
        booking.setAssessmentID(assessment_id);
        booking.setRequestID(request_id);
        booking.setStatus(status);
        booking.setStudentID(student_id);
        booking.setRemark(remark);
    }

    public void getFieldData() {
        module = ModuleComboBox.getSelectedItem().toString();
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        if (time != null && date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = date.format(dateFormatter);
            request_date = (formattedDate + "," + time.toString());
        }
        supervisor = supervisorField.getText().trim();
    }

    public void setBookingData(Object[] rowData) {
        request_id = rowData[0].toString();

        String[] modules = new String[]{rowData[2].toString()};
        ModuleComboBox.setModel(new DefaultComboBoxModel<>(modules));
        supervisorField.setText(rowData[4].toString());
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        requestArray = user.seachUser(id, "request.txt", null);
        assessmentArray = user.seachUser(id, "assessment.txt", null);
        fs.showFileData(PresentationRquestPanel.requestTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
        fs.showFileData(PresentationRquestPanel.assessmentTable, assessment_columns, "assessment.txt", assessmentArray, 0);
    }

    public void resetField() {
        dateTimePicker1.datePicker.setDate(null); // Set date component to null
        dateTimePicker1.timePicker.setTime(null); // Set time component to null
        ModuleComboBox.setSelectedItem("Introduction to Programming");
        supervisorField.setText("");
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
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel2 = new javax.swing.JLabel();
        ModuleComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        SubmitBtn = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        supervisorField = new javax.swing.JTextField();

        jLabel1.setText("Date :");

        jLabel2.setText("Module :");

        ModuleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Supervisor: ");

        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        ResetBtn.setText("Reset");
        ResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBtnActionPerformed(evt);
            }
        });

        CancelBtn.setText("Cancel");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });

        supervisorField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supervisorFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(SubmitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(ResetBtn)
                        .addGap(33, 33, 33)
                        .addComponent(CancelBtn)
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supervisorField)
                            .addComponent(ModuleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ModuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitBtn)
                    .addComponent(ResetBtn)
                    .addComponent(CancelBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        Booking booking = new Booking();
        getFieldData();
        setFieldData(booking);
        boolean alreadyExists = fs.checkExists("request.txt", booking.getBooking(), "assessment_id");
        List<String> errors = new ArrayList<>();
        validateString(supervisor, "Supervisor", errors);
        validateString(request_date, "Request Date", errors);

        if (assessment_id != null) {
            if (!assessment_type.isEmpty()) {
                if (errors.isEmpty()) {
                    if (!alreadyExists) {
                        booking.saveFile("request.txt");
                    } else {
                        JOptionPane.showMessageDialog(null, "Request has been sended, Please be patient", "Request Error", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Module not has any assessmnet yet", "Submission Error", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a module", "Selection Error", JOptionPane.WARNING_MESSAGE);

        }

        refreshTable();
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        resetField();
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Cancel Request?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            fs.deleteData(request_id, "request.txt", "ID");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Your request remains active");
        }
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void supervisorFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supervisorFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supervisorFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JComboBox<String> ModuleComboBox;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JButton SubmitBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JTextField supervisorField;
    // End of variables declaration//GEN-END:variables
}
