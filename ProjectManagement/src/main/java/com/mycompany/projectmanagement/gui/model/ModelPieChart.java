/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement.gui.model;

import java.util.HashMap;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author yizun
 */
public class ModelPieChart {

    private final String title;
    private final HashMap<String, Integer> data;

    public ModelPieChart(String title, HashMap<String, Integer> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach((key, count) -> {
            if (count != 0) {
                String capitalizedKey = key.substring(0, 1).toUpperCase() + key.substring(1);
                dataset.setValue(capitalizedKey, count);
            }
        });
        return dataset;
    }
}
