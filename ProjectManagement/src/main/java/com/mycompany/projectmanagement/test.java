/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import com.mycompany.projectmanagement.FileController.Course;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

/**
 *
 * @author yizun
 */
public class test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<TimelineEvent> events = new ArrayList<>();
            events.add(new TimelineEvent("Event 1", 2000));
            events.add(new TimelineEvent("Event 2", 2005));
            events.add(new TimelineEvent("Event 3", 2010));
            events.add(new TimelineEvent("Event 4", 2015));
            events.add(new TimelineEvent("Event 5", 2020));

            JFrame frame = new JFrame("Timeline Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TimelinePanel(events));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class TimelineEvent {

    String description;
    int year;

    TimelineEvent(String description, int year) {
        this.description = description;
        this.year = year;
    }
}

class TimelinePanel extends JPanel {

    private List<TimelineEvent> events;

    public TimelinePanel(List<TimelineEvent> events) {
        this.events = events;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int margin = 50;
        int startX = margin;
        int endX = width - margin;
        int y = height / 2;

        // Draw timeline line
        g2d.drawLine(startX, y, endX, y);

        // Draw events
        int eventCount = events.size();
        int spacing = (endX - startX) / (eventCount - 1);
        for (int i = 0; i < eventCount; i++) {
            TimelineEvent event = events.get(i);
            int x = startX + i * spacing;
            g2d.fillOval(x - 5, y - 5, 10, 10);
            g2d.drawString(event.description + " (" + event.year + ")", x - 20, y - 10);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 200);
    }
}
