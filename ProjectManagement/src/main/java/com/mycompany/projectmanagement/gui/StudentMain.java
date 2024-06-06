/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectmanagement.gui;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.gui.panel.ModelCard;
import com.mycompany.projectmanagement.gui.panel.PieChart;
import com.mycompany.projectmanagement.gui.panel.PieChart.PieChartData;
import com.mycompany.projectmanagement.gui.panel.PresentationRquestPanel;
import com.mycompany.projectmanagement.gui.panel.StudentDashboardPanel;
import com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel;
import static com.mycompany.projectmanagement.gui.panel.StudentDashboardPanel.card1;
import static com.mycompany.projectmanagement.gui.panel.StudentDashboardPanel.card2;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class StudentMain extends javax.swing.JFrame {

    int mouseX, mouseY;
    public String id, name;
    private final FileController.FileService fs;
    private final UserController userController;
    private String imagePath;
    private JSONArray reportArray;
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

    public StudentMain() {
        initComponents();
        this.fs = new FileController.FileService();
        this.userController = new UserController();
        this.pieChartData = new ArrayList<>();

    }

    public void setUser(String id) {
        this.id = id;
        refreshTable();
        UserController.User user = userController.new User();
        JSONArray searchedArray = user.seachUser(id, "student.txt", null);
        JSONObject searchObj = searchedArray.getJSONObject(0);
        this.name = searchObj.getString("Name");
        this.imagePath = searchObj.getString("ImagePath");
        refreshTable();

        String[] status = {"Pending", "Late", "Accepted", "Rejected"};
        reportArray = user.seachUser(id, "report.txt", null);
        JSONArray requestArray = user.seachUser(id, "request.txt", null);

        HashMap<String, Integer> report = fs.countOccurrences("report.txt", "status", status, reportArray);
        HashMap<String, Integer> reqeust = fs.countOccurrences("reqeust.txt", "status", status, requestArray);
        card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/user_icon.png")), "Pending Report", report.get("Pending").toString(), "Report that need to be mark"));
        card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/mycompany/projectmanagement/icon/projects_icon.png")), "Pendin Request", reqeust.get("Pending").toString(), "Request for presentation"));

        pieChartData.add(new PieChart.PieChartData("Report Percentage", "report.txt", "grade", grades));
        pieChartData.add(new PieChart.PieChartData("Request Percentage", "request.txt", "status", status));

        studentDashboardPanel2.pieChart1.setData(pieChartData, reportArray);
        studentDashboardPanel2.pieChart1.refreshPieChart(pieChartData, reportArray);
    }

    public void setHeader(String title) {
        String projectDirectory = System.getProperty("user.dir");
        File image = new File(projectDirectory + imagePath);
        header1.setData(new ModelHeader(title, name, new ImageIcon(image.toString())));

    }

    public void refreshTable() {
        UserController.User user = userController.new User();
        JSONArray requestArray = user.seachUser(id, "request.txt", null);
        reportArray = user.seachUser(id, "report.txt", null);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        header1 = new com.mycompany.projectmanagement.gui.Header();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        studentDashboardPanel2 = new com.mycompany.projectmanagement.gui.panel.StudentDashboardPanel();
        reportSubmissionPanel1 = new com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel();
        presentationRequestPanel = new com.mycompany.projectmanagement.gui.panel.PresentationRquestPanel();
        studentMenu2 = new com.mycompany.projectmanagement.gui.StudentMenu();
        Btn1 = new javax.swing.JButton();
        Btn2 = new javax.swing.JButton();
        Btn3 = new javax.swing.JButton();
        Btn4 = new javax.swing.JButton();

        jTabbedPane1.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Form 2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Form 3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

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
        getContentPane().add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1650, -1));

        jTabbedPane3.addTab("tab3", studentDashboardPanel2);
        jTabbedPane3.addTab("tab4", reportSubmissionPanel1);

        presentationRequestPanel.setOpaque(false);
        jTabbedPane3.addTab("tab3", presentationRequestPanel);

        getContentPane().add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 1450, 840));

        studentMenu2.setPreferredSize(new java.awt.Dimension(200, 672));

        Btn1.setText("Dashboard");
        Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn1ActionPerformed(evt);
            }
        });

        Btn2.setText("Submit Report");
        Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn2ActionPerformed(evt);
            }
        });

        Btn3.setText("Request Presentation");
        Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn3ActionPerformed(evt);
            }
        });

        Btn4.setText("Sign Out");
        Btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout studentMenu2Layout = new javax.swing.GroupLayout(studentMenu2);
        studentMenu2.setLayout(studentMenu2Layout);
        studentMenu2Layout.setHorizontalGroup(
            studentMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(Btn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        studentMenu2Layout.setVerticalGroup(
            studentMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentMenu2Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(388, Short.MAX_VALUE))
        );

        getContentPane().add(studentMenu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 51, -1, 810));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        dispose();
    }//GEN-LAST:event_Btn4ActionPerformed

    private void Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn3ActionPerformed
        jTabbedPane3.setSelectedIndex(2);
        presentationRequestPanel.setUser(id);

    }//GEN-LAST:event_Btn3ActionPerformed

    private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
        jTabbedPane3.setSelectedIndex(1);
        reportSubmissionPanel1.setUser(id);
    }//GEN-LAST:event_Btn2ActionPerformed

    private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed

        jTabbedPane3.setSelectedIndex(0);
        studentDashboardPanel2.setUser(id);
    }//GEN-LAST:event_Btn1ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn1;
    private javax.swing.JButton Btn2;
    private javax.swing.JButton Btn3;
    private javax.swing.JButton Btn4;
    private com.mycompany.projectmanagement.gui.Header header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.mycompany.projectmanagement.gui.panel.PresentationRquestPanel presentationRequestPanel;
    private com.mycompany.projectmanagement.gui.panel.ReportSubmissionPanel reportSubmissionPanel1;
    private com.mycompany.projectmanagement.gui.panel.StudentDashboardPanel studentDashboardPanel2;
    private com.mycompany.projectmanagement.gui.StudentMenu studentMenu2;
    // End of variables declaration//GEN-END:variables
}
