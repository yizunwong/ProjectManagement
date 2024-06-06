/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.dashboard;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.model.ModelCard;
import static com.mycompany.projectmanagement.gui.panel.AccountPanel.columns;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.json.JSONArray;

/**
 *
 * @author yizun
 */
public class AdminDashboard extends javax.swing.JPanel {

    private final UserController userController;

    /**
     * Creates new form AdminTest
     */
    public AdminDashboard() {
        initComponents();
        this.userController = new UserController();
        initialCard();
    }

    public final void initialCard() {
        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(userTable, columns, "account.txt", null,0);
        UserController.User user = userController.new User();
        String total_member = user.countTotalUser();
        String[] status = {"In Progress"};
        HashMap<String, Integer> assessment = fs.countOccurrences("assessment.txt", "status", status, null);
        card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/user_icon.png")), "Total Member", total_member, "Registered account"));
        card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/projects_icon.png")), "Total Project", assessment.get("In Progress").toString(), "Project that is active"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.mycompany.projectmanagement.gui.component.Card();
        card2 = new com.mycompany.projectmanagement.gui.component.Card();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        pieChart1 = new com.mycompany.projectmanagement.gui.component.PieChart();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        card1.setColor1(new java.awt.Color(153, 204, 255));
        add(card1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 160));

        card2.setColor1(new java.awt.Color(255, 204, 102));
        add(card2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 300, 160));

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
        jScrollPane1.setViewportView(userTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 1520, 380));

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
        });
        add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 300, -1));
        add(pieChart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 1210, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchValue = searchField.getText();
            FileController.FileService fs = new FileController.FileService();
            UserController.User user = userController.new User();
            JSONArray searchedArray = user.seachUser(searchValue, "account.txt",null);
            fs.showFileData(userTable, columns, "account.txt", searchedArray,0);
        }
    }//GEN-LAST:event_searchFieldKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.projectmanagement.gui.component.Card card1;
    private com.mycompany.projectmanagement.gui.component.Card card2;
    private javax.swing.JScrollPane jScrollPane1;
    public com.mycompany.projectmanagement.gui.component.PieChart pieChart1;
    private javax.swing.JTextField searchField;
    public static javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}