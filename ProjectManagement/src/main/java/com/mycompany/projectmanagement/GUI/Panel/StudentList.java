/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import java.awt.event.KeyEvent;
import org.json.JSONArray;
//import java.awt.event.ActionEvent;
//import javax.swing.Timer;

/**
 *
 * @author yizun
 */
public class StudentList extends javax.swing.JPanel {

    public final static String[] columns = {"ID", "Name", "Parent Name", "IC",
        "Phone.no", "Gender", "Country", "Address", "Email", "Birth Date",
        "Entry Level", "Course", "Intake Date", "ImagePath"};
//    private final Timer timer;
    private final UserController userController;

    /**
     * Creates new form Student
     */
    public StudentList() {
        initComponents();
        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(userTable, columns, "student.txt", null);
        this.userController = new UserController();

//        //refresh table every 30 seconds
//        timer = new Timer(30000, (ActionEvent e) -> {
//            // Code to refresh your JTable data
//            fs.showFileData(userTable, columns, "student.txt");
//        });
//        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentPanel = new com.mycompany.projectmanagement.gui.panel.StudentPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();

        studentPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(studentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        int rowIndex = userTable.getSelectedRow(); // Get the selected row index

        // Ensure a valid row is selected
        if (rowIndex != -1) {
            int columnCount = userTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = userTable.getModel().getValueAt(rowIndex, columnIndex);
            }

            // Pass the data to the edit form
            studentPanel.setData(rowData);
        }
    }//GEN-LAST:event_userTableMouseClicked

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = searchField.getText();
            System.out.println(searchValue);
            FileController.FileService fs = new FileController.FileService();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "student.txt");
            fs.showFileData(userTable, columns, "student.txt", searchedArray);

        }
    }//GEN-LAST:event_searchFieldKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    public static com.mycompany.projectmanagement.gui.panel.StudentPanel studentPanel;
    public static javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
