/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectmanagement.gui;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.panel.LecturerDashboard;
import static com.mycompany.projectmanagement.gui.panel.LecturerDashboard.card1;
import static com.mycompany.projectmanagement.gui.panel.LecturerDashboard.card2;
import static com.mycompany.projectmanagement.gui.panel.LecturerDashboard.columns;
import com.mycompany.projectmanagement.gui.panel.ModelCard;
import com.mycompany.projectmanagement.gui.panel.PieChart;
import com.mycompany.projectmanagement.gui.panel.PieChart.PieChartData;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;

public class LecturerMain extends javax.swing.JFrame {

    private String id, name;
    private final UserController userController;
    private final FileController.FileService fs;
    private JSONArray assessmentArray;
    private String imagePath;
    private final ArrayList<PieChartData> pieChartData;
    String[] grades = {
        "A+ (Distinction)",
        "A (Distinction)",
        "B+ (Credit)",
        "B (Credit)",
        "C (Pass)",
        "D (Pass)",
        "F+ (Marginal Fail)",
        "F (Fail)",
        "-"
    };
    private JSONArray reportArray;
    int mouseX, mouseY;

    public LecturerMain() {
        initComponents();
        this.userController = new UserController();
        this.fs = new FileController.FileService();
        this.pieChartData = new ArrayList<>();

    }

    public void setUser(String id) {
        this.id = id;
        UserController.User user = userController.new User();
        JSONArray searchedArray = user.seachUser(id, "lecturer.txt", null);
        JSONObject searchObj = searchedArray.getJSONObject(0);
        this.name = searchObj.getString("Name");
        this.imagePath = searchObj.getString("ImagePath");
        refreshTable();

        String[] status = {"Pending", "Late", "Accepted", "Rejected"};
        reportArray = user.seachUser(name, "report.txt", null);
        JSONArray requestArray = user.seachUser(name, "request.txt", null);

        HashMap<String, Integer> report = fs.countOccurrences("report.txt", "status", status, reportArray);
        HashMap<String, Integer> reqeust = fs.countOccurrences("reqeust.txt", "status", status, requestArray);
        card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/user_icon.png")), "Pending Report", report.get("Pending").toString(), "Report that need to be mark"));
        card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/projects_icon.png")), "Pendin Request", reqeust.get("Pending").toString(), "Request for presentation"));

        pieChartData.add(new PieChart.PieChartData("Report Percentage", "report.txt", "grade", grades));
        pieChartData.add(new PieChart.PieChartData("Request Percentage", "request.txt", "status", status));

        lecturerDashboard.pieChart1.setData(pieChartData, reportArray);
        lecturerDashboard.pieChart1.refreshPieChart(pieChartData, reportArray);
    }

    public void setHeader(String title) {
        String projectDirectory = System.getProperty("user.dir");
        File image = new File(projectDirectory + imagePath);
        header1.setData(new ModelHeader(title, name, new ImageIcon(image.toString())));

    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        assessmentArray = user.seachUser(name, "assessment.txt", null);
        fs.showFileData(LecturerDashboard.superviseeTable, columns, "assessment.txt", assessmentArray, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1 = new com.mycompany.projectmanagement.gui.Header();
        lecturerMenu1 = new com.mycompany.projectmanagement.gui.LecturerMenu();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        lecturerDashboard = new com.mycompany.projectmanagement.gui.panel.LecturerDashboard();
        verifyBookingPanel = new com.mycompany.projectmanagement.gui.panel.VerifyBookingPanel();
        markPanel = new com.mycompany.projectmanagement.gui.panel.MarkingPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        getContentPane().add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 60));

        jButton1.setText("DashBoard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Review Presentation Request");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Marking Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Sign Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lecturerMenu1Layout = new javax.swing.GroupLayout(lecturerMenu1);
        lecturerMenu1.setLayout(lecturerMenu1Layout);
        lecturerMenu1Layout.setHorizontalGroup(
            lecturerMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lecturerMenu1Layout.setVerticalGroup(
            lecturerMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lecturerMenu1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        getContentPane().add(lecturerMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 940));

        jTabbedPane1.addTab("tab1", lecturerDashboard);
        jTabbedPane1.addTab("tab2", verifyBookingPanel);
        jTabbedPane1.addTab("tab3", markPanel);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 1480, 920));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        markPanel.setUser(name);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        verifyBookingPanel.setUser(name);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        lecturerDashboard.setUser(name);
        lecturerDashboard.pieChart1.refreshPieChart(pieChartData, reportArray);


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LecturerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecturerMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.projectmanagement.gui.Header header1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.mycompany.projectmanagement.gui.panel.LecturerDashboard lecturerDashboard;
    private com.mycompany.projectmanagement.gui.LecturerMenu lecturerMenu1;
    private com.mycompany.projectmanagement.gui.panel.MarkingPanel markPanel;
    private com.mycompany.projectmanagement.gui.panel.VerifyBookingPanel verifyBookingPanel;
    // End of variables declaration//GEN-END:variables
}
