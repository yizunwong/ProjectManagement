/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.panel;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel.assessment_columns;
import java.awt.event.KeyEvent;
import org.json.JSONArray;

public class PresentationRquestPanel extends javax.swing.JPanel {

    public final static String[] columns = {"ID", "student_id", "assessment_id","module", "request_date", "supervisor", "status", "remark"};
    public UserController userController;
    private String id;
    private JSONArray requestArray, assessmentArray;
    private final FileController.FileService fs;

    public PresentationRquestPanel() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();
        fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
        fs.showFileData(PresentationRquestPanel.assessmentTable, assessment_columns, "assessment.txt", assessmentArray, 0);
    }

    public void setUser(String id) {
        this.id = id;
        System.out.println(id);
        refreshTable();
        presentationRequestForm1.setUser(id);
    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        requestArray = user.seachUser(id, "request.txt");
        assessmentArray = user.seachUser(id, "assessment.txt");
        fs.showFileData(requestTable, PresentationRquestPanel.columns, "request.txt", requestArray, 0);
        fs.showFileData(PresentationRquestPanel.assessmentTable, assessment_columns, "assessment.txt", assessmentArray, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lecturerSearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        assessmentTable = new javax.swing.JTable();
        presentationRequestForm1 = new com.mycompany.projectmanagement.gui.panel.PresentationRequestForm();

        lecturerSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lecturerSearchFieldKeyPressed(evt);
            }
        });

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
        jScrollPane2.setViewportView(requestTable);

        assessmentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        assessmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assessmentTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(assessmentTable);

        presentationRequestForm1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presentationRequestForm1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lecturerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturerSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(presentationRequestForm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lecturerSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lecturerSearchFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = lecturerSearchField.getText();
            System.out.println(searchValue);
            FileController.FileService fs = new FileController.FileService();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "lecturer.txt");
        }
    }//GEN-LAST:event_lecturerSearchFieldKeyPressed

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        int SelectedRow = requestTable.getSelectedRow(); // Get the selected row index
        // Ensure a valid row is selected
        if (SelectedRow != -1) {
            int columnCount = requestTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = requestTable.getModel().getValueAt(SelectedRow, columnIndex);
            }
            presentationRequestForm1.setBookingData(rowData);

        }
    }//GEN-LAST:event_requestTableMouseClicked

    private void assessmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assessmentTableMouseClicked
        // TODO add your handling code here:
        int SelectedRow = assessmentTable.getSelectedRow(); // Get the selected row index
        // Ensure a valid row is selected
        if (SelectedRow != -1) {
            int columnCount = assessmentTable.getColumnCount(); // Get the number of columns

            // Retrieve data from the table model for the clicked row
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = assessmentTable.getModel().getValueAt(SelectedRow, columnIndex);
            }

            // Pass the data to the edit form
            presentationRequestForm1.setAssessmentData(rowData);

        }
    }//GEN-LAST:event_assessmentTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable assessmentTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lecturerSearchField;
    private com.mycompany.projectmanagement.gui.panel.PresentationRequestForm presentationRequestForm1;
    public static javax.swing.JTable requestTable;
    // End of variables declaration//GEN-END:variables
}
