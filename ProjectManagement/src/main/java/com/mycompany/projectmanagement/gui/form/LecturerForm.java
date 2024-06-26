/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.form;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Area;
import com.mycompany.projectmanagement.FileController.State;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.UserController.Account;
import com.mycompany.projectmanagement.UserController.Lecturer;
import com.mycompany.projectmanagement.UserController.User.FileType;
import com.mycompany.projectmanagement.gui.panel.LecturerPanel;
import static com.mycompany.projectmanagement.Validator.validateDate;
import static com.mycompany.projectmanagement.Validator.validateEmail;
import static com.mycompany.projectmanagement.Validator.validateIC;
import static com.mycompany.projectmanagement.Validator.validatePhone;
import static com.mycompany.projectmanagement.Validator.validateString;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author yizun
 */
public class LecturerForm extends javax.swing.JPanel {

    private String name, ic, phone, gender, country, address, email, id, formattedDate, department, role, state;
    private Date dob;
    private File imagePath;
    public String education = "default";
    private final UserController userController;
    private final String projectDirectory;
    private File selectedFile;
    public String fileName;
    private final FileController.FileService fs;

    /**
     * Creates new form EditPanel
     */
    public LecturerForm() {
        initComponents();
        this.projectDirectory = System.getProperty("user.dir");
        this.imagePath = new File("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg");
        this.userController = new UserController();
        initComponents();
        initializeComboBox();
        dobChooser.setLocale(Locale.ENGLISH);
        maleBtn.setSelected(true);
        this.fs = new FileController.FileService();
    }

    public void setFile(String fileName) {
        this.fileName = fileName;
        if (fileName.equalsIgnoreCase("project_manager.txt")) {
            role = "project manager";
        } else {
            role = "lecturer";
        }

    }

    private void initializeComboBox() {
        State st = new State();
        Area area = new Area();
        String[] states = st.getStates();
        String[] departments = area.findDepartment();
        stateComboBox.setModel(new DefaultComboBoxModel<>(states));
        departmentComboBox.setModel(new DefaultComboBoxModel<>(departments));
    }

    public void setData(Object[] rowData) {
        idField.setText(rowData[0].toString());
        nameField.setText(rowData[1].toString());
        icField.setText(rowData[2].toString());
        phoneField.setText(rowData[3].toString());
        if (rowData[4].toString().equalsIgnoreCase("male")) {
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }
        stateComboBox.setSelectedItem(rowData[5].toString());
        addressField.setText(rowData[6].toString());
        emailField.setText(rowData[7].toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = dateFormat.parse(rowData[8].toString());
            dobChooser.setLocale(Locale.ENGLISH);
            dobChooser.setDate(date);
        } catch (ParseException e) {

        }
        departmentComboBox.setSelectedItem(rowData[9].toString());
        entryLevelComboBox.setSelectedItem(rowData[10].toString());
        this.imagePath = new File(rowData[11].toString());
        UserController.User.updateAvatarImageIcon(imagePath, avatarImageIcon);
    }

    public void resetField() {

        idField.setText("");
        nameField.setText("");
        icField.setText("");
        phoneField.setText("");
        maleBtn.setSelected(true);
        stateComboBox.setSelectedItem("Andorra");
        addressField.setText("");
        emailField.setText("");
        dobChooser.setDate(null);
        entryLevelComboBox.setSelectedItem("-");
        departmentComboBox.setSelectedItem("-");
        this.imagePath = new File("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg");
        UserController.User.updateAvatarImageIcon(imagePath, avatarImageIcon);

    }

    public void getFieldData() {
        id = idField.getText().trim();
        name = nameField.getText().trim();
        ic = icField.getText().trim();
        phone = phoneField.getText().trim();
        state = stateComboBox.getSelectedItem().toString();
        address = addressField.getText().trim();
        email = emailField.getText().trim();
        dob = dobChooser.getDate();
        if (dob != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            formattedDate = dateFormat.format(dob);
        }
        education = entryLevelComboBox.getSelectedItem().toString();
        department = departmentComboBox.getSelectedItem().toString();
        if (maleBtn.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

    }

    public void setFieldData(Lecturer lecturer, Account account) {
        lecturer.setName(name);
        lecturer.setIc(ic);
        lecturer.setPhone(phone);
        lecturer.setGender(gender);
        lecturer.setState(state);
        lecturer.setAddress(address);
        lecturer.setEmail(email);
        lecturer.setDob(formattedDate);
        lecturer.setEducation(education);
        lecturer.setDepartment(department);
        lecturer.setFullPath(imagePath);
        if (idField.getText().trim().isEmpty()) {
            String newID = fs.generateUniqueId("lecturer", "account.txt", "ID");
            lecturer.setId(newID);
            idField.setText(lecturer.getID());
        } else {
            id = idField.getText().trim();
            lecturer.setId(id);
        }

        account.setId(lecturer.id);
    }

    public List<String> validateField() {
        List<String> errors = new ArrayList<>();
        validateString(name, "Name", errors);
        validateIC(ic, errors);
        validatePhone(phone, errors);
        validateString(state, "State", errors);
        validateString(address, "Address", errors);
        validateEmail(email, errors);
        validateDate(dob, errors);
        validateString(department, "Department", errors);
        validateString(education, "Education", errors);
        return errors;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderRadioGroup = new javax.swing.ButtonGroup();
        saveBtn = new javax.swing.JButton();
        phoneField = new javax.swing.JTextField();
        idLabel = new javax.swing.JLabel();
        maleBtn = new javax.swing.JRadioButton();
        countryLabel = new javax.swing.JLabel();
        fileUploadBtn = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        icField = new javax.swing.JTextField();
        dobChooser = new com.toedter.calendar.JDateChooser();
        icLabel = new javax.swing.JLabel();
        entryLevelComboBox = new javax.swing.JComboBox<>();
        avatarImageIcon = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox<>();
        genderLabel = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        entryLevelLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        dobLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        femaleBtn = new javax.swing.JRadioButton();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        entryLevelLabel1 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();

        saveBtn.setText("Add");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        idLabel.setText("ID :");

        genderRadioGroup.add(maleBtn);
        maleBtn.setText("Male");

        countryLabel.setText("State :");

        fileUploadBtn.setText("Upload");
        fileUploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileUploadBtnActionPerformed(evt);
            }
        });

        icLabel.setText("IC :");

        entryLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Foundation", "Diploma", "Degree", "Masters Degree", "PhD" }));
        entryLevelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryLevelComboBoxActionPerformed(evt);
            }
        });

        avatarImageIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        avatarImageIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\yizun\\OneDrive\\Documents\\NetBeansProjects\\ProjectManagement\\src\\main\\java\\com\\mycompany\\projectmanagement\\default-avatar-icon-of-social-media-user-vector.jpg")); // NOI18N
        avatarImageIcon.setMaximumSize(new java.awt.Dimension(120, 120));

        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        genderLabel.setText("Gender :");

        entryLevelLabel.setText("Education");

        addressLabel.setText("Address :");

        idField.setEditable(false);

        dobLabel.setText("Date of Birth : ");

        phoneLabel.setText("Phone no. :");

        genderRadioGroup.add(femaleBtn);
        femaleBtn.setText("Female");

        nameLabel.setText("Name :");

        emailLabel.setText("Email : ");

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        entryLevelLabel1.setText("Department : ");

        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fileUploadBtn)
                .addGap(339, 339, 339))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(idLabel)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(avatarImageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(entryLevelLabel1)
                                    .addComponent(icLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                        .addComponent(icField))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maleBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(femaleBtn))
                                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                            .addComponent(countryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addressField)
                                            .addComponent(stateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dobLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(emailField)
                                            .addComponent(entryLevelComboBox, 0, 248, Short.MAX_VALUE)
                                            .addComponent(dobChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(entryLevelLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(resetBtn)
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn)
                                .addGap(18, 18, 18)
                                .addComponent(updateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deleteBtn)
                                .addGap(168, 168, 168)))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(avatarImageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idLabel)
                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileUploadBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icLabel)))
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(maleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(femaleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(entryLevelLabel1)
                            .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entryLevelLabel)
                            .addComponent(entryLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveBtn)
                            .addComponent(updateBtn)
                            .addComponent(resetBtn)
                            .addComponent(deleteBtn)))
                    .addComponent(dobChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        UserController.Account account = userController.new Account();
        UserController.Lecturer lecturer = userController.new Lecturer();
        getFieldData();
        setFieldData(lecturer, account);
        List<String> errors = validateField();
        boolean alreadyExists = fs.checkExists("lecturer.txt", lecturer.getLecturer(), "ID");
        if (errors.isEmpty()) {
            if (!alreadyExists) {
                lecturer.saveTextFile(fileName);
                account.setAccount(role);
                account.saveTextFile("account.txt");
                lecturer.saveFile(selectedFile);
            } else {
                JOptionPane.showMessageDialog(null, "Data Exists", "Duplciate Data", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

        }
        fs.showFileData(LecturerPanel.userTable, LecturerPanel.columns, fileName, null, 1);

    }//GEN-LAST:event_saveBtnActionPerformed

    private void fileUploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileUploadBtnActionPerformed
        // TODO add your handling code here:
        UserController.User user = userController.new User();
        user.setUploadPath("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\", FileType.IMAGE);
        this.imagePath = user.getFullPath();
        if (imagePath == null) {
            this.imagePath = new File("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg");
        }
        this.selectedFile = user.getSelectedFile();
        UserController.User.updateAvatarImageIcon(imagePath, avatarImageIcon);

    }//GEN-LAST:event_fileUploadBtnActionPerformed

    private void entryLevelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryLevelComboBoxActionPerformed
        // TODO add your handling code here:
        this.education = entryLevelComboBox.getSelectedItem().toString();

    }//GEN-LAST:event_entryLevelComboBoxActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Update Data?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            UserController.Lecturer lecturer = userController.new Lecturer();
            UserController.Account account = userController.new Account();
            getFieldData();
            setFieldData(lecturer, account);

            List<String> errors = validateField();
            boolean alreadyExists = fs.checkExists("student.txt", lecturer.getLecturer(), "ID");
            if (errors.isEmpty()) {
                if (!alreadyExists) {
                    lecturer.updateTextFile(fileName);
                    lecturer.saveFile(selectedFile);
                    JOptionPane.showMessageDialog(null, "Data update successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Data Exists", "Duplciate Data", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Data update cancel");
        }
        fs.showFileData(LecturerPanel.userTable, LecturerPanel.columns, fileName, null, 1);
    }//GEN-LAST:event_updateBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        resetField();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Delete Data?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            getFieldData();
            fs.deleteData(id, fileName, "ID");
            fs.deleteData(id, "account.txt", "ID");
            fs.showFileData(LecturerPanel.userTable, LecturerPanel.columns, fileName, null, 1);
        } else {
            JOptionPane.showMessageDialog(null, "Data delete cancel");
        }

    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel avatarImageIcon;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<String> departmentComboBox;
    private com.toedter.calendar.JDateChooser dobChooser;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JComboBox<String> entryLevelComboBox;
    private javax.swing.JLabel entryLevelLabel;
    private javax.swing.JLabel entryLevelLabel1;
    private javax.swing.JRadioButton femaleBtn;
    private javax.swing.JButton fileUploadBtn;
    private javax.swing.JLabel genderLabel;
    private javax.swing.ButtonGroup genderRadioGroup;
    private javax.swing.JTextField icField;
    private javax.swing.JLabel icLabel;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JRadioButton maleBtn;
    public javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton resetBtn;
    public javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
