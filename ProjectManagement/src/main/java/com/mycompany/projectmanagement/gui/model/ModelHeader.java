/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement.gui.model;

import javax.swing.ImageIcon;

/**
 *
 * @author yizun
 */
public class ModelHeader {

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public ModelHeader(String title, String username, ImageIcon icon) {
        this.title = title;
        this.icon = icon;
        this.username = username;
    }

    public ModelHeader() {
    }

    private ImageIcon icon;
    private String title;
    private String username;
}
