/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectmanagement.gui;

import com.mycompany.projectmanagement.gui.model.ModelHeader;
import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel;
import com.mycompany.projectmanagement.gui.component.PieChart;
import com.mycompany.projectmanagement.gui.dashboard.PorjectManagerDashboard;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public class ProjectManagerMenu extends javax.swing.JFrame {

    private final ArrayList<PieChart.PieChartData> pieChartData;
    private final FileController.FileService fs;
    private String id, name;
    private final UserController userController;
    private String imagePath;
    private int mouseX, mouseY;

    /**
     * Creates new form AdminMenu
     */
    public ProjectManagerMenu() {
        initComponents();
        this.userController = new UserController();

        this.fs = new FileController.FileService();
        this.pieChartData = new ArrayList<>();
        pieChartData.add(new PieChart.PieChartData("Type Percentage", "assessment.txt", "assessment_type",
                new String[]{"Internship Report", "Investigation Report", "CP1", "CP2", "RMCP", "FYP"}));
        pieChartData.add(new PieChart.PieChartData("Status Percentage", "assessment.txt", "status",
                new String[]{"In Progress", "Late", "Completed"}));
        porjectManagerDashboard.pieChart1.setData(pieChartData, null);
        porjectManagerDashboard.pieChart1.refreshPieChart(pieChartData, null);
    }

    public void setUser(String id) {
        this.id = id;
        UserController.User user = userController.new User();
        JSONArray searchedArray = user.seachUser(id, "project_manager.txt", null);
        JSONObject searchObj = searchedArray.getJSONObject(0);
        this.name = searchObj.getString("Name");
        this.imagePath = searchObj.getString("ImagePath");

    }

    public void setHeader(String title) {
        String projectDirectory = System.getProperty("user.dir");
        File image = new File(projectDirectory + imagePath);
        header1.setData(new ModelHeader(title, name, new ImageIcon(image.toString())));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.mycompany.projectmanagement.gui.component.Header();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        porjectManagerDashboard = new com.mycompany.projectmanagement.gui.dashboard.PorjectManagerDashboard();
        assignAssessmentPanel = new com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel();
        sideNavigationMenu = new com.mycompany.projectmanagement.gui.model.SideNavigationMenu();
        dashboardBtn = new javax.swing.JButton();
        manageAssessmentBtn = new javax.swing.JButton();
        signoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header1.setBackground(new java.awt.Color(51, 51, 51));
        header1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                header1MouseDragged(evt);
            }
        });
        header1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                header1MousePressed(evt);
            }
        });
        getContentPane().add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1790, -1));

        jTabbedPane1.addTab("tab2", porjectManagerDashboard);
        jTabbedPane1.addTab("tab1", assignAssessmentPanel);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 1610, 920));

        sideNavigationMenu.setPreferredSize(new java.awt.Dimension(200, 672));

        dashboardBtn.setText("Dashboard");
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        manageAssessmentBtn.setText("Manage Assessment");
        manageAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAssessmentBtnActionPerformed(evt);
            }
        });

        signoutBtn.setText("Sign Out");
        signoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideNavigationMenuLayout = new javax.swing.GroupLayout(sideNavigationMenu);
        sideNavigationMenu.setLayout(sideNavigationMenuLayout);
        sideNavigationMenuLayout.setHorizontalGroup(
            sideNavigationMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addComponent(manageAssessmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(signoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sideNavigationMenuLayout.setVerticalGroup(
            sideNavigationMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideNavigationMenuLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageAssessmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234)
                .addComponent(signoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(412, Short.MAX_VALUE))
        );

        getContentPane().add(sideNavigationMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 160, 910));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        porjectManagerDashboard.initialCard();
        porjectManagerDashboard.pieChart1.refreshPieChart(pieChartData, null);
        fs.showFileData(PorjectManagerDashboard.dataTable, AssignAssessmentPanel.assessment_columns, "assessment.txt", null, 0);
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void manageAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageAssessmentBtnActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        fs.showFileData(AssignAssessmentPanel.dataTable, AssignAssessmentPanel.assessment_columns, "assessment.txt", null, 0);
    }//GEN-LAST:event_manageAssessmentBtnActionPerformed

    private void signoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutBtnActionPerformed
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_signoutBtnActionPerformed

    private void header1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_header1MousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_header1MousePressed

    private void header1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_header1MouseDragged
        // TODO add your handling code here:
        setLocation(evt.getXOnScreen() - mouseX, evt.getYOnScreen() - mouseY);

    }//GEN-LAST:event_header1MouseDragged

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
            java.util.logging.Logger.getLogger(ProjectManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectManagerMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.mycompany.projectmanagement.gui.panel.AssignAssessmentPanel assignAssessmentPanel;
    private javax.swing.JButton dashboardBtn;
    private com.mycompany.projectmanagement.gui.component.Header header1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton manageAssessmentBtn;
    private com.mycompany.projectmanagement.gui.dashboard.PorjectManagerDashboard porjectManagerDashboard;
    private com.mycompany.projectmanagement.gui.model.SideNavigationMenu sideNavigationMenu;
    private javax.swing.JButton signoutBtn;
    // End of variables declaration//GEN-END:variables
}