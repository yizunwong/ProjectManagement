/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import static com.mycompany.projectmanagement.JSONHandler.getValues;
import static com.mycompany.projectmanagement.JSONHandler.treeToJson;
import static com.mycompany.projectmanagement.gui.form.ManageCourseForm.entrylist;
import static com.mycompany.projectmanagement.gui.panel.ManageCoursePanel.jTree1;
import static com.mycompany.projectmanagement.gui.panel.ManageCoursePanel.node;
import static com.mycompany.projectmanagement.gui.panel.ManageCoursePanel.root;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public class JtreeController {

    public static void buildTree(DefaultMutableTreeNode root, JSONArray areas) {
        for (int i = 0; i < areas.length(); i++) {
            JSONObject area = areas.getJSONObject(i);
            DefaultMutableTreeNode areaNode = new DefaultMutableTreeNode(area.getString("area"));
            root.add(areaNode);

            JSONObject programs = area.getJSONObject("programs");
            JSONObject intakeDates = area.getJSONObject("intake_dates");

            for (String programType : programs.keySet()) {
                DefaultMutableTreeNode programTypeNode = new DefaultMutableTreeNode(programType);
                areaNode.add(programTypeNode);
                addIntakeDates(programTypeNode, intakeDates, programType);
                addPrograms(programTypeNode, programs.getJSONArray(programType));
            }
        }
    }

    private static void addIntakeDates(DefaultMutableTreeNode parent, JSONObject intakeDates, String programType) {
        List<String> datesList = getValues(intakeDates, programType, true);
        if (!datesList.isEmpty()) {
            DefaultMutableTreeNode intakeDatesNode = new DefaultMutableTreeNode("Intake Dates");
            parent.add(intakeDatesNode);
            datesList.forEach(date -> intakeDatesNode.add(new DefaultMutableTreeNode(date)));
        }
    }

    private static void addPrograms(DefaultMutableTreeNode parent, JSONArray programArray) {
        for (int j = 0; j < programArray.length(); j++) {
            JSONObject program = programArray.getJSONObject(j);
            DefaultMutableTreeNode programNode = new DefaultMutableTreeNode(program.getString("name"));
            parent.add(programNode);

            addCourseId(programNode, program.getString("id"));
            addModules(programNode, program);
        }
    }

    private static void addCourseId(DefaultMutableTreeNode parent, String courseId) {
        DefaultMutableTreeNode courseIdNode = new DefaultMutableTreeNode("Course ID");
        parent.add(courseIdNode);
        courseIdNode.add(new DefaultMutableTreeNode(courseId));
    }

    private static void addModules(DefaultMutableTreeNode parent, JSONObject program) {
        List<String> modulesList = getValues(program, "modules", false);
        if (!modulesList.isEmpty()) {
            DefaultMutableTreeNode modulesNode = new DefaultMutableTreeNode("Modules");
            parent.add(modulesNode);

            String[] modulesArray = modulesList.get(0).replaceAll("[\"\\[\\]]", "").split(",");
            for (String module : modulesArray) {
                modulesNode.add(new DefaultMutableTreeNode(module.trim()));
            }
        }
    }

    public static boolean checkNode(DefaultMutableTreeNode newNode) {
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            if (childNode.toString().equals(newNode.toString())) {
                JOptionPane.showMessageDialog(null, "A node with the same data already exists.", "Duplicate Node", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public static boolean matchParent(DefaultMutableTreeNode parent, String targetParent) {
        if (parent.toString().equalsIgnoreCase(targetParent)) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Wrong parent to add.", "Wrong Node", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    public static void addNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
        parent.add(child);
        ((DefaultTreeModel) jTree1.getModel()).reload(parent);
        treeToJson(root);
    }

    public static boolean checkParentInArray(DefaultMutableTreeNode parent) {
        if (entrylist.contains(parent.toString())) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Wrong parent to add.", "Wrong Node", JOptionPane.WARNING_MESSAGE);
        return false;
    }
}
