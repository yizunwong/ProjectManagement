/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectmanagement.GUI;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.GUI.Panel.AccountList;
import com.mycompany.projectmanagement.GUI.Panel.StudentList;

/**
 *
 * @author yizun
 */
public class SecondMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public SecondMenu() {
        initComponents();
//        setExtendedState(MainMenu.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        assignAssessmentPanel2 = new com.mycompany.projectmanagement.GUI.Panel.AssignAssessmentPanel();
        jPanel2 = new javax.swing.JPanel();
        manageStudentBtn = new javax.swing.JButton();
        manageLecturerBtn = new javax.swing.JButton();
        manageAccountBtn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        managePMBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addTab("tab1", assignAssessmentPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1580, 840));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        manageStudentBtn.setText("Assign Assessment");
        manageStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStudentBtnActionPerformed(evt);
            }
        });

        manageLecturerBtn.setText("Manage Assessment");
        manageLecturerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageLecturerBtnActionPerformed(evt);
            }
        });

        manageAccountBtn.setText("Manage Account");
        manageAccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAccountBtnActionPerformed(evt);
            }
        });

        jButton4.setText("Sign Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        managePMBtn.setText("Manage PM");
        managePMBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePMBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageStudentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageLecturerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageAccountBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(managePMBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(manageStudentBtn)
                .addGap(18, 18, 18)
                .addComponent(manageLecturerBtn)
                .addGap(18, 18, 18)
                .addComponent(managePMBtn)
                .addGap(18, 18, 18)
                .addComponent(manageAccountBtn)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(496, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 840));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStudentBtnActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(StudentList.userTable, StudentList.columns, "student.txt", null);

    }//GEN-LAST:event_manageStudentBtnActionPerformed

    private void manageAccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageAccountBtnActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        FileController.FileService fs = new FileController.FileService();
        fs.showFileData(AccountList.userTable, AccountList.columns, "account.txt", null);
    }//GEN-LAST:event_manageAccountBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void managePMBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePMBtnActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
//        jTabbedPane1.setSelectedIndex(1);
//        lecturerList.setFile("project_manager.txt");
//        FileController.FileService fs = new FileController.FileService();
//        fs.showFileData(LecturerList.userTable, LecturerList.columns, lecturerList.fileName, null);
    }//GEN-LAST:event_managePMBtnActionPerformed

    private void manageLecturerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageLecturerBtnActionPerformed
        // TODO add your handling code here:
//        jTabbedPane1.setSelectedIndex(1);
//        FileController.FileService fs = new FileController.FileService();
//        lecturerList.setFile("lecturer.txt");
//        fs.showFileData(LecturerList.userTable, LecturerList.columns, "lecturer.txt", null);
    }//GEN-LAST:event_manageLecturerBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SecondMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecondMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecondMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecondMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecondMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.projectmanagement.GUI.Panel.AssignAssessmentPanel assignAssessmentPanel2;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton manageAccountBtn;
    private javax.swing.JButton manageLecturerBtn;
    private javax.swing.JButton managePMBtn;
    private javax.swing.JButton manageStudentBtn;
    // End of variables declaration//GEN-END:variables
}
