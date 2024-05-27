/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Booking;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.DefaultComboBoxModel;

public class RequestDatePanel extends javax.swing.JPanel {

    private String module;
    private String request_date;
    private String lecturer;
    private String request_id;
//    private String status;
    private String student_id;
//    private String remark;

    public RequestDatePanel() {
        initComponents();
        initializeComboBox();
    }

    public void setFieldData(Booking booking) {
        booking.setLecturer(lecturer);
        booking.setModule(module);
        booking.setRequestDate(request_date);
        booking.setRequestID(request_id);
        booking.setStatus("Pending");
        booking.setStudentID(student_id);
        booking.setRemark("abc");
    }

    public void setCancelFieldData(Booking booking) {
        booking.setLecturer(lecturer);
        booking.setModule(module);
        booking.setRequestDate(request_date);
        booking.setRequestID(request_id);
        booking.setStatus("Cancelled");
        booking.setStudentID(student_id);
        booking.setRemark("abc");
    }
    
    public void getFieldData() {
        module = ModuleComboBox.getSelectedItem().toString();
        LocalTime time = dateTimePicker1.timePicker.getTime();
        LocalDate date = dateTimePicker1.datePicker.getDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = date.format(dateFormatter);
        request_date = (formattedDate + "," + time.toString());
        lecturer = LecturerField.getText().trim();
        request_id = RequestField.getText().trim();
        student_id = StudentIDField.getText().trim();
    }

    private void initializeComboBox() {
        FileController.Course cr = new FileController.Course();
        String[] modules = cr.findModule(null, null);
        ModuleComboBox.setModel(new DefaultComboBoxModel<>(modules));
    }

    void setBookingData(Object[] rowData) {
        RequestField.setText(rowData[0].toString());
        StudentIDField.setText(rowData[1].toString());
        String dateTimeString = rowData[2].toString();

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
        ModuleComboBox.setSelectedItem(rowData[3].toString());
        LecturerField.setText(rowData[4].toString());
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
        RequestField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LecturerField = new javax.swing.JTextField();
        SubmitBtn = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        StudentIDField = new javax.swing.JTextField();

        jLabel1.setText("Request Presentation Date :");

        jLabel2.setText("Module :");

        ModuleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Lecturer :");

        jLabel4.setText("Request ID :");

        LecturerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LecturerFieldActionPerformed(evt);
            }
        });

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

        jLabel5.setText("Student ID :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31)
                        .addComponent(RequestField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel5)
                        .addGap(36, 36, 36)
                        .addComponent(StudentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LecturerField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModuleComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(SubmitBtn)
                            .addGap(65, 65, 65)
                            .addComponent(ResetBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CancelBtn))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(RequestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(StudentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ModuleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LecturerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitBtn)
                    .addComponent(ResetBtn)
                    .addComponent(CancelBtn))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        Booking booking = new Booking();
        getFieldData();
        setFieldData(booking);
        booking.saveFile("Request.txt");

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(RequestDateList.BookingTable, RequestDateList.columns, "Request.txt", null,0);
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        RequestField.setText("");
        StudentIDField.setText("");
        dateTimePicker1.datePicker.setDate(null); // Set date component to null
        dateTimePicker1.timePicker.setTime(null); // Set time component to null
        ModuleComboBox.setSelectedItem("Introduction to Programming");
        LecturerField.setText("");
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void LecturerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LecturerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LecturerFieldActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        Booking booking = new Booking();
        getFieldData();
        setCancelFieldData(booking);
        booking.updateFile("Request.txt", booking.getBooking());

        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(RequestDateList.BookingTable, RequestDateList.columns, "Request.txt", null,0);
    }//GEN-LAST:event_CancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    public static javax.swing.JTextField LecturerField;
    private javax.swing.JComboBox<String> ModuleComboBox;
    private javax.swing.JTextField RequestField;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JTextField StudentIDField;
    private javax.swing.JButton SubmitBtn;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}