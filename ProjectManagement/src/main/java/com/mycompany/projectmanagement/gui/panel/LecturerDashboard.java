/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.json.JSONArray;

public class LecturerDashboard extends javax.swing.JPanel {

    public final static String[] columns = {"supervisor", "second_marker", "assessment_id", "student_id", "course_id", "intake_date", "module", "assessment_type",
        "status", "due_time"};
    private final UserController userController;
    public String name;
    private JSONArray assessmentArray;
    private final FileController.FileService fs;

    public LecturerDashboard() {
        initComponents();
        this.fs = new FileController.FileService();
        this.userController = new UserController();
        fs.showFileData(superviseeTable, columns, "assessment.txt", null, 0);
//        UserController.User user = userController.new User();
//        String total_member = user.countTotalUser();

    }

    public void setUser(String name) {
        this.name = name;
        refreshTable();

    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        assessmentArray = user.seachUser(name, "assessment.txt");
        fs.showFileData(superviseeTable, columns, "assessment.txt", assessmentArray, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        superviseeTable = new javax.swing.JTable();
        pieChart1 = new com.mycompany.projectmanagement.gui.panel.PieChart();
        card1 = new com.mycompany.projectmanagement.gui.panel.Card();
        card2 = new com.mycompany.projectmanagement.gui.panel.Card();

        superviseeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(superviseeTable);

        card1.setColor1(new java.awt.Color(153, 204, 255));

        card2.setColor1(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.mycompany.projectmanagement.gui.panel.Card card1;
    public static com.mycompany.projectmanagement.gui.panel.Card card2;
    private javax.swing.JScrollPane jScrollPane1;
    public com.mycompany.projectmanagement.gui.panel.PieChart pieChart1;
    public static javax.swing.JTable superviseeTable;
    // End of variables declaration//GEN-END:variables
}
