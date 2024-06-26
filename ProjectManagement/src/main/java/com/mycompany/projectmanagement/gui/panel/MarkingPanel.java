/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import java.awt.event.KeyEvent;
import org.json.JSONArray;

/**
 *
 * @author Asus
 */
public class MarkingPanel extends javax.swing.JPanel {

    public UserController userController;
    public String name;
    private JSONArray reportArray;
    private final FileController.FileService fs;

    public MarkingPanel() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();
        fs.showFileData(reportTable, ReportSubmissionPanel.columns, "report.txt", null, 0);
    }

    public void setUser(String name) {
        this.name = name;
        refreshTable();
        markingForm1.setUser(name);
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        reportArray = user.seachUser(name, "report.txt",null);
        fs.showFileData(MarkingPanel.reportTable, ReportSubmissionPanel.columns, "report.txt", reportArray, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        markingForm1 = new com.mycompany.projectmanagement.gui.form.MarkingForm();

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
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
        reportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(reportTable);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        markingForm1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markingForm1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(markingForm1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableMouseClicked
        int SelectedRow = reportTable.getSelectedRow(); // Get the selected row index
        // Ensure a valid row is selected
        if (SelectedRow != -1) {
            int columnCount = reportTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = reportTable.getModel().getValueAt(SelectedRow, columnIndex);
            }

            // Pass the data to the edit form
            markingForm1.setReportData(rowData);

        }
    }//GEN-LAST:event_reportTableMouseClicked

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = searchField.getText();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "report.txt",reportArray);
            fs.showFileData(reportTable, ReportSubmissionPanel.columns, "report.txt", searchedArray, 0);
        }
    }//GEN-LAST:event_searchFieldKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private com.mycompany.projectmanagement.gui.form.MarkingForm markingForm1;
    public static javax.swing.JTable reportTable;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
