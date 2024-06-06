/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel.assessment_columns;
import static com.mycompany.projectmanagement.gui.panel.PresentationRquestPanel.requestTable;
import org.json.JSONArray;

/**
 *
 * @author Asus
 */
public class StudentDashboardPanel extends javax.swing.JPanel {

    private String id;
    private final FileController.FileService fs;
    private final UserController userController;

    /**
     * Creates new form StudentDashboardList
     */
    public StudentDashboardPanel() {
        initComponents();
        this.fs = new FileController.FileService();
        this.userController = new UserController();
        
    }

    public void setUser(String id) {
        this.id = id;
        System.out.println(id);
        refreshTable();
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        JSONArray requestArray = user.seachUser(id, "request.txt");
        JSONArray reportArray = user.seachUser(id, "report.txt");
        fs.showFileData(StudentDashboardPanel.SubmissionTable, ReportSubmissionPanel.columns, "report.txt", reportArray, 1);
        fs.showFileData(StudentDashboardPanel.BookingTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
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
        SubmissionTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        BookingTable = new javax.swing.JTable();
        card1 = new com.mycompany.projectmanagement.gui.panel.Card();
        card2 = new com.mycompany.projectmanagement.gui.panel.Card();
        pieChart1 = new com.mycompany.projectmanagement.gui.panel.PieChart();

        SubmissionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Assessment Type", "Supervisor", "Second Marker", "Due Date", "Module", "File Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(SubmissionTable);

        BookingTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(BookingTable);

        card1.setColor1(new java.awt.Color(153, 204, 255));

        card2.setColor1(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 1113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable BookingTable;
    public static javax.swing.JTable SubmissionTable;
    public static com.mycompany.projectmanagement.gui.panel.Card card1;
    public static com.mycompany.projectmanagement.gui.panel.Card card2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public com.mycompany.projectmanagement.gui.panel.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}