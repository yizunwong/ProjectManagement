/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectmanagement.gui.form;

import com.mycompany.projectmanagement.FileController;
import com.mycompany.projectmanagement.FileController.Assessment;
import com.mycompany.projectmanagement.FileController.Course;
import com.mycompany.projectmanagement.FileController.State;
import com.mycompany.projectmanagement.UserController;
import com.mycompany.projectmanagement.UserController.Account;
import com.mycompany.projectmanagement.UserController.Student;
import com.mycompany.projectmanagement.UserController.User.FileType;
import com.mycompany.projectmanagement.gui.panel.StudentPanel;
import static com.mycompany.projectmanagement.Validator.validateDate;
import static com.mycompany.projectmanagement.Validator.validateEmail;
import static com.mycompany.projectmanagement.Validator.validatePhone;
import static com.mycompany.projectmanagement.Validator.validateString;
import static com.mycompany.projectmanagement.Validator.validateIC;
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
public class StudentForm extends javax.swing.JPanel {

    private String name, parent_name, ic, phone, gender, state, address, email, intake_date, id, formattedDate, course_id;
    private Date dob;
    private File imagePath;
    private String[] courses, modules;
    private String selectedCourse;
    public String entry_level;
    private final UserController userController;
    private File selectedFile;
    private final FileController.FileService fs;

    /**
     * Creates new form EditPanel
     */
    public StudentForm() {
        this.imagePath = new File("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg");
        this.entry_level = "default";
        initComponents();
        initializeCountryComboBox();
        this.userController = new UserController();
        dobChooser.setLocale(Locale.ENGLISH);
        maleBtn.setSelected(true);
        this.fs = new FileController.FileService();

    }

    private void initializeCountryComboBox() {
        State st = new State();
        String[] states = st.getStates();
        stateComboBox.setModel(new DefaultComboBoxModel<>(states));
    }

    public void setData(Object[] rowData) {
        idField.setText(rowData[0].toString());
        nameField.setText(rowData[1].toString());
        parentNameField.setText(rowData[2].toString());
        icField.setText(rowData[3].toString());
        phoneField.setText(rowData[4].toString());
        if (rowData[5].toString().equalsIgnoreCase("male")) {
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }
        stateComboBox.setSelectedItem(rowData[6].toString());
        addressField.setText(rowData[7].toString());
        emailField.setText(rowData[8].toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = dateFormat.parse(rowData[9].toString());
            dobChooser.setLocale(Locale.ENGLISH);
            dobChooser.setDate(date);
        } catch (ParseException e) {

        }

        entryLevelComboBox.setSelectedItem(rowData[10].toString());
        courseComboBox.setSelectedItem(rowData[11].toString());
        intakeComboBox.setSelectedItem(rowData[12].toString());
        this.imagePath = new File(rowData[13].toString());
        UserController.User.updateAvatarImageIcon(imagePath, avatarImageIcon);

    }

    public void resetField() {
        idField.setText("");
        nameField.setText("");
        parentNameField.setText("");
        icField.setText("");
        phoneField.setText("");
        maleBtn.setSelected(true);
        stateComboBox.setSelectedItem("Andorra");
        addressField.setText("");
        emailField.setText("");
        dobChooser.setDate(null);
        entryLevelComboBox.setSelectedItem("-");
        courseComboBox.setSelectedItem("-");
        intakeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"-"}));

        this.imagePath = new File("\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg");
        UserController.User.updateAvatarImageIcon(imagePath, avatarImageIcon);

    }

    public void getFieldData() {
        id = idField.getText().trim();
        name = nameField.getText().trim();
        ic = icField.getText().trim();
        phone = phoneField.getText().trim();
        parent_name = parentNameField.getText().trim();
        state = stateComboBox.getSelectedItem().toString();
        address = addressField.getText().trim();
        email = emailField.getText().trim();
        entry_level = entryLevelComboBox.getSelectedItem().toString();
        selectedCourse = courseComboBox.getSelectedItem().toString();
        intake_date = intakeComboBox.getSelectedItem().toString();

        if (maleBtn.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        dob = dobChooser.getDate();
        if (dob != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            formattedDate = dateFormat.format(dob);
        }
        if (!selectedCourse.equalsIgnoreCase("-") && !entry_level.equalsIgnoreCase("-")) {
            Course course = new Course();
            course_id = course.findCourseID(entry_level, selectedCourse);
            modules = course.findModule(entry_level, selectedCourse);
        }

    }

    public void setFieldData(Student student, Account account, Assessment assessment) {
        student.setName(name);
        student.setParent_name(parent_name);
        student.setIc(ic);
        student.setPhone(phone);
        student.setGender(gender);
        student.setState(state);
        student.setAddress(address);
        student.setEmail(email);
        student.setDob(formattedDate);
        student.setEntry_level(entry_level);
        student.setCourse(selectedCourse);
        student.setFullPath(imagePath);
        student.setIntake_date(intake_date);
        if (idField.getText().trim().isEmpty()) {
            String newID = fs.generateUniqueId("student", "account.txt", "ID");
            student.setId(newID);
            idField.setText(student.getID());
        } else {
            id = idField.getText().trim();
            student.setId(id);
        }
        account.setId(student.id);
        assessment.setCourseID(course_id);
        assessment.setStudentID(student.id);
        assessment.setModules(modules);
        assessment.setIntakeDate(intake_date);
    }

    public List<String> validateField() {
        List<String> errors = new ArrayList<>();
        validateString(name, "Name", errors);
        validateString(parent_name, "Parent Name", errors);
        validateIC(ic, errors);
        validatePhone(phone, errors);
        validateString(state, "State", errors);
        validateString(address, "Address", errors);
        validateEmail(email, errors);
        validateDate(dob, errors);
        validateString(entry_level, "Entry Level", errors);
        validateString(selectedCourse, "Course", errors);
        validateString(intake_date, "Intake Date", errors);
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
        courseComboBox = new javax.swing.JComboBox<>();
        entryLevelLabel = new javax.swing.JLabel();
        intakeComboBox = new javax.swing.JComboBox<>();
        addressLabel = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        parentNameField = new javax.swing.JTextField();
        dobLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        femaleBtn = new javax.swing.JRadioButton();
        courseLabel = new javax.swing.JLabel();
        parentNameLabel = new javax.swing.JLabel();
        intakeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
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

        courseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        courseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseComboBoxActionPerformed(evt);
            }
        });

        entryLevelLabel.setText("Entry Level :");

        intakeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        addressLabel.setText("Address :");

        idField.setEditable(false);

        dobLabel.setText("Date of Birth : ");

        phoneLabel.setText("Phone no. :");

        genderRadioGroup.add(femaleBtn);
        femaleBtn.setText("Female");

        courseLabel.setText("Course :");

        parentNameLabel.setText("Parent Name : ");

        intakeLabel.setText("Intake :");

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
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idLabel)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(avatarImageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(parentNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(icLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameField)
                                    .addComponent(parentNameField)
                                    .addComponent(icField)
                                    .addComponent(phoneField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(entryLevelComboBox, 0, 245, Short.MAX_VALUE)
                                            .addComponent(courseComboBox, 0, 245, Short.MAX_VALUE)
                                            .addComponent(dobChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(intakeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maleBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(femaleBtn)
                                .addGap(143, 143, 143)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(courseLabel)
                                    .addComponent(entryLevelLabel)
                                    .addComponent(intakeLabel))
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
                                .addGap(169, 169, 169)))
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
                            .addComponent(parentNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parentNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(icField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icLabel)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dobChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phoneLabel)
                                .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(femaleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(entryLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entryLevelLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intakeLabel)
                    .addComponent(intakeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(updateBtn)
                    .addComponent(resetBtn)
                    .addComponent(deleteBtn))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed

        UserController.Account account = userController.new Account();
        UserController.Student student = userController.new Student();
        FileController.Assessment assessment = new FileController.Assessment();

        getFieldData();
        setFieldData(student, account, assessment);
        List<String> errors = validateField();
        boolean alreadyExists = fs.checkExists("student.txt", student.getStudent(), "ID");
        if (errors.isEmpty()) {
            if (!alreadyExists) {
                student.saveTextFile("student.txt");
                account.setAccount("student");
                account.saveTextFile("account.txt");
                assessment.saveTextFile("assessment.txt", assessment);
                student.saveFile(selectedFile);
            } else {
                JOptionPane.showMessageDialog(null, "Data Exists", "Duplciate Data", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

        }

        fs.showFileData(StudentPanel.userTable, StudentPanel.columns, "student.txt", null, 1);
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
        UserController.User.updateAvatarImageIcon(selectedFile, avatarImageIcon);
    }//GEN-LAST:event_fileUploadBtnActionPerformed

    private void entryLevelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryLevelComboBoxActionPerformed
        // TODO add your handling code here:
        this.entry_level = entryLevelComboBox.getSelectedItem().toString();
        Course course = new Course();
        if ("-".equals(entry_level)) {
            intakeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"-"}));
        }
        this.courses = course.getCourse(entry_level);
        courseComboBox.setModel(new DefaultComboBoxModel<>(courses));
    }//GEN-LAST:event_entryLevelComboBoxActionPerformed

    private void courseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseComboBoxActionPerformed
        this.selectedCourse = courseComboBox.getSelectedItem().toString();
        Course course = new Course();
        String[] intake = course.findIntake(selectedCourse, entry_level);
        intakeComboBox.setModel(new DefaultComboBoxModel<>(intake));
    }//GEN-LAST:event_courseComboBoxActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Update Data?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            UserController.Student student = userController.new Student();
            UserController.Account account = userController.new Account();
            FileController.Assessment assessment = new FileController.Assessment();
            getFieldData();
            setFieldData(student, account, assessment);
            List<String> errors = validateField();
            if (errors.isEmpty()) {
                student.updateTextFile("student.txt");
                assessment.replaceData("assessment.txt", assessment);
                student.saveFile(selectedFile);

                JOptionPane.showMessageDialog(null, "Data update successfully");
                fs.showFileData(StudentPanel.userTable, StudentPanel.columns, "student.txt", null, 1);

            } else {
                JOptionPane.showMessageDialog(null, errors.get(0), "Validation Error", JOptionPane.WARNING_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(null, "Data update cancel");
        }
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
            fs.deleteData(id, "student.txt", "ID");
            fs.deleteData(id, "account.txt", "ID");
            fs.deleteData(id, "assessment.txt", "student_id");

            fs.showFileData(StudentPanel.userTable, StudentPanel.columns, "student.txt", null, 1);
        } else {
            JOptionPane.showMessageDialog(null, "Data delete cancel");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel avatarImageIcon;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JComboBox<String> courseComboBox;
    private javax.swing.JLabel courseLabel;
    private javax.swing.JButton deleteBtn;
    private com.toedter.calendar.JDateChooser dobChooser;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JComboBox<String> entryLevelComboBox;
    private javax.swing.JLabel entryLevelLabel;
    private javax.swing.JRadioButton femaleBtn;
    private javax.swing.JButton fileUploadBtn;
    private javax.swing.JLabel genderLabel;
    private javax.swing.ButtonGroup genderRadioGroup;
    private javax.swing.JTextField icField;
    private javax.swing.JLabel icLabel;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JComboBox<String> intakeComboBox;
    private javax.swing.JLabel intakeLabel;
    private javax.swing.JRadioButton maleBtn;
    public javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField parentNameField;
    private javax.swing.JLabel parentNameLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton resetBtn;
    public javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
