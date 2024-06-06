/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import java.awt.event.KeyEvent;
import org.json.JSONArray;

public class VerifyBookingPanel extends javax.swing.JPanel {

    public final static String[] Presentation_columns = {"ID", "request_id", "student_id", "presentation_date", "assessment_id", "supervisor", "status", "remark"};
    public UserController userController;
    private String name;
    private JSONArray requestArray;
    public FileController.FileService fs;
    private JSONArray presentationArray;

    public VerifyBookingPanel() {
        initComponents();
        this.userController = new UserController();

        this.fs = new FileController.FileService();
        fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", null, 0);
        fs.showFileData(presentationTable, Presentation_columns, "presentation.txt", null, 0);
    }

    public void setUser(String name) {
        this.name = name;
        System.out.println(name);
        refreshTable();
        verifyBookingForm1.setUser(name);
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        presentationArray = user.seachUser(name, "presentation.txt");
        requestArray = user.seachUser(name, "request.txt");
        fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
        fs.showFileData(presentationTable, Presentation_columns, "presentation.txt", presentationArray, 0);
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
        requestTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        presentationTable = new javax.swing.JTable();
        verifyBookingForm1 = new com.mycompany.projectmanagement.gui.panel.VerifyBookingForm();

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
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
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(requestTable);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        presentationTable.setModel(new javax.swing.table.DefaultTableModel(
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
        presentationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                presentationTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(presentationTable);

        verifyBookingForm1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1456, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verifyBookingForm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(verifyBookingForm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void presentationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_presentationTableMouseClicked
//        int rowIndex = presentationTable.getSelectedRow(); // Get the selected row index
//
//        // Ensure a valid row is selected
//        if (rowIndex != -1) {
//            int columnCount = presentationTable.getColumnCount(); // Get the number of columns
//
//            // Retrieve data from the table model for the clicked row
//            Object[] rowData = new Object[columnCount];
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                rowData[columnIndex] = presentationTable.getModel().getValueAt(rowIndex, columnIndex);
//            }
//
//            verifyBookingForm1.setRequestData(rowData);
//
//        }
    }//GEN-LAST:event_presentationTableMouseClicked

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = searchField.getText();
            System.out.println(searchValue);
            FileController.FileService fs = new FileController.FileService();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "request.txt");
            fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", null, 0);
            System.out.println(searchedArray);
        }
    }//GEN-LAST:event_searchFieldKeyPressed

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = requestTable.getSelectedRow(); // Get the selected row index

        // Ensure a valid row is selected
        if (rowIndex != -1) {
            int columnCount = requestTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = requestTable.getModel().getValueAt(rowIndex, columnIndex);
            }

            verifyBookingForm1.setRequestData(rowData);

        }
    }//GEN-LAST:event_requestTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable presentationTable;
    public static javax.swing.JTable requestTable;
    private javax.swing.JTextField searchField;
    private com.mycompany.projectmanagement.gui.panel.VerifyBookingForm verifyBookingForm1;
    // End of variables declaration//GEN-END:variables
}
