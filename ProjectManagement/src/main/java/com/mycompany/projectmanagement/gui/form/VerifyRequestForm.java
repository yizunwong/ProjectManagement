/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.form;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Presentation;
import com.mycompany.projectmanagement.FileController.Request;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.panel.PresentationRquestPanel;
import static com.mycompany.projectmanagement.Validator.validateString;
import static com.mycompany.projectmanagement.gui.panel.VerifyRequestPanel.Presentation_columns;
import static com.mycompany.projectmanagement.gui.panel.VerifyRequestPanel.presentationTable;
import static com.mycompany.projectmanagement.gui.panel.VerifyRequestPanel.requestTable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class VerifyRequestForm extends javax.swing.JPanel {

    private String module, request_date, supervisor, request_id, status, student_id, presentation_id, remark, name;
    private final UserController userController;
    private final FileController.FileService fs;
    private JSONArray requestArray;
    private JSONArray presentationArray;

    public VerifyRequestForm() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();

    }

    public void setUser(String name) {
        this.name = name;

    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        presentationArray = user.seachUser(name, "presentation.txt", null);
        requestArray = user.seachUser(name, "request.txt", null);
        fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
        fs.showFileData(presentationTable, Presentation_columns, "presentation.txt", presentationArray, 0);
    }

    public void getFieldData() {
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        if (time != null && date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = date.format(dateFormatter);
            request_date = (formattedDate + "," + time.toString());
        }
        supervisor = supervisorField.getText().trim();
        request_id = RequestField.getText().trim();
        status = StatusComboBox.getSelectedItem().toString();
        remark = remarkTextArea.getText().trim();

    }

    public void setFieldData(Presentation prsentation) {
        if (presentation_id == null) {
            presentation_id = fs.generateUniqueId("presentation", "presentation.txt", "ID");
        }
        prsentation.setPresentationID(presentation_id);
        prsentation.setLecturer(supervisor);
        prsentation.setModule(module);
        prsentation.setRequestDate(request_date);
        prsentation.setRequestID(request_id);
        prsentation.setStatus(status);
        prsentation.setStudentID(student_id);
        prsentation.setRemark(remark);
    }

    public void setRequestData(Object[] rowData) {
        this.presentation_id = null;
        RequestField.setText(rowData[0].toString());
        student_id = rowData[1].toString();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate date = LocalDate.parse(rowData[4].toString().split(",")[0], dateFormatter);
        LocalTime time = LocalTime.parse(rowData[4].toString().split(",")[1], timeFormatter);
        dateTimePicker1.datePicker.setDate(date);
        dateTimePicker1.timePicker.setTime(time);
        module = rowData[3].toString();
        supervisorField.setText(rowData[5].toString());
        status = rowData[6].toString();
        StatusComboBox.setSelectedItem(rowData[6].toString());
        remarkTextArea.setText(rowData[7].toString());
    }

    public void setPresentationData(Object[] rowData) {
        this.presentation_id = rowData[0].toString();
        RequestField.setText(rowData[1].toString());
        student_id = rowData[2].toString();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate date = LocalDate.parse(rowData[3].toString().split(",")[0], dateFormatter);
        LocalTime time = LocalTime.parse(rowData[3].toString().split(",")[1], timeFormatter);
        dateTimePicker1.datePicker.setDate(date);
        dateTimePicker1.timePicker.setTime(time);
        module = rowData[4].toString();
        supervisorField.setText(rowData[5].toString());
        status = rowData[6].toString();
        StatusComboBox.setSelectedItem(status);
        remarkTextArea.setText(rowData[7].toString());
    }

    public void resetField() {
        RequestField.setText("");
        dateTimePicker1.datePicker.setDate(null);
        dateTimePicker1.timePicker.setTime(null);
        supervisorField.setText("");
        remarkTextArea.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        RequestField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel6 = new javax.swing.JLabel();
        supervisorField = new javax.swing.JTextField();
        SubmitBtn = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        StatusComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarkTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jLabel2.setText("Request ID :");

        jLabel4.setText("Date :");

        jLabel6.setText("Supervisor:");

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

        jLabel7.setText("Status :");

        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Accepted", "Rejected", "Completed" }));

        jLabel8.setText("Remark :");

        remarkTextArea.setColumns(20);
        remarkTextArea.setRows(5);
        jScrollPane1.setViewportView(remarkTextArea);

        jButton1.setText("Update");
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
                        .addGap(83, 83, 83)
                        .addComponent(SubmitBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(23, 23, 23)
                        .addComponent(ResetBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(RequestField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(StatusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(supervisorField, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(RequestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(supervisorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitBtn)
                    .addComponent(ResetBtn)
                    .addComponent(jButton1))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed

        if (status.equalsIgnoreCase("Pending")) {
            Presentation presentation = new Presentation();
            Request request = new Request();
            getFieldData();
            setFieldData(presentation);
            List<String> errors = new ArrayList<>();
            validateString(request_id, "Request", errors);
            presentation.setStatus("On Going");
            if (!status.equalsIgnoreCase("Rejected")) {
                if (errors.isEmpty()) {
                    boolean alreadyExists = fs.checkExists("presentation.txt", presentation.getPresentation(), "request_id");
                    if (!alreadyExists) {
                        presentation.saveFile("presentation.txt");

                        UserController.User user = userController.new User();
                        JSONArray searchedArray = user.seachUser(request_id, "request.txt", null);
                        JSONObject searchObj = searchedArray.getJSONObject(0);
                        searchObj.put("status", status);
                        request.updateFile("request.txt", searchObj);

                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Request has been accepted", "Request Error", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                UserController.User user = userController.new User();
                JSONArray searchedArray = user.seachUser(request_id, "request.txt", null);
                JSONObject searchObj = searchedArray.getJSONObject(0);
                searchObj.put("status", status);
                request.updateFile("request.txt", searchObj);

                refreshTable();
                JOptionPane.showMessageDialog(null, "Request has been rejected", "Request Error", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Request has been accepted/rejected", "Request Error", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        // TODO add your handling code here:
        resetField();
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Presentation presentation = new Presentation();
        getFieldData();
        setFieldData(presentation);
        System.out.println(presentation.getPresentation());
        presentation.updateFile("presentation.txt", presentation.getPresentation());

        refreshTable();
        JOptionPane.showMessageDialog(null, "Presentation Updated");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RequestField;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JButton SubmitBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea remarkTextArea;
    private javax.swing.JTextField supervisorField;
    // End of variables declaration//GEN-END:variables
}
