/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.gui.panel.RequestDatePanel.LecturerField;
import java.awt.event.KeyEvent;
import org.json.JSONArray;


public class RequestDateList extends javax.swing.JPanel {


    public final static String[] lecturer_columns = {"ID", "Name", "IC", "Gender", "Email", "Department", "Education"};
    public final static String[] columns = {"Request_ID", "Student_ID", "Request_Date", "Module", "Lecturer", "Status", "Remark"};
    public UserController userController;
    private String selectedLecturer;
    
    public RequestDateList() {
        initComponents();
        this.userController =  new UserController();
        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(userTable, lecturer_columns, "lecturer.txt", null,0);
        fs.showFileData(BookingTable, columns, "Request.txt", null,0);
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
        userTable = new javax.swing.JTable();
        lecturerSearchField = new javax.swing.JTextField();
        AssignLecturerBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        BookingTable = new javax.swing.JTable();
        requestDatePanel1 = new com.mycompany.projectmanagement.gui.panel.RequestDatePanel();

        userTable.setModel(new javax.swing.table.DefaultTableModel(
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
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        lecturerSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lecturerSearchFieldKeyPressed(evt);
            }
        });

        AssignLecturerBtn.setText("Assign Lecturer");
        AssignLecturerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignLecturerBtnActionPerformed(evt);
            }
        });

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
        BookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookingTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BookingTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lecturerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(AssignLecturerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(requestDatePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lecturerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignLecturerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(requestDatePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(8, 8, 8))
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
            
            this.selectedLecturer = rowData[0].toString();
        }
    }//GEN-LAST:event_userTableMouseClicked

    private void lecturerSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lecturerSearchFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = lecturerSearchField.getText();
            System.out.println(searchValue);
            FileController.FileService fs = new FileController.FileService();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "lecturer.txt");
            fs.showFileData(userTable, lecturer_columns, "lecturer.txt", searchedArray,0);
        }
    }//GEN-LAST:event_lecturerSearchFieldKeyPressed

    private void AssignLecturerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignLecturerBtnActionPerformed
        LecturerField.setText(selectedLecturer);
    }//GEN-LAST:event_AssignLecturerBtnActionPerformed

    private void BookingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookingTableMouseClicked
        int SelectedRow = BookingTable.getSelectedRow(); // Get the selected row index
        // Ensure a valid row is selected
        if (SelectedRow != -1) {
            int columnCount = BookingTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = BookingTable.getModel().getValueAt(SelectedRow, columnIndex);
            }

            // Pass the data to the edit form
            requestDatePanel1.setBookingData(rowData);
        }
    }//GEN-LAST:event_BookingTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AssignLecturerBtn;
    public static javax.swing.JTable BookingTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lecturerSearchField;
    private com.mycompany.projectmanagement.gui.panel.RequestDatePanel requestDatePanel1;
    public static javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}