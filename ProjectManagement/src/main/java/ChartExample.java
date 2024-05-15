
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class ChartExample extends JFrame {

    public ChartExample() {
        super("Chart Example");

        // Create a dataset for pie chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Category 1", 10);
        pieDataset.setValue("Category 2", 20);
        pieDataset.setValue("Category 3", 30);

        // Create pie chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Pie Chart Example",
                pieDataset,
                true,
                true,
                false
        );

        // Create a dataset for histogram (bar chart)
        DefaultCategoryDataset histogramDataset = new DefaultCategoryDataset();
        histogramDataset.addValue(10, "Series 1", "Category 1");
        histogramDataset.addValue(20, "Series 1", "Category 2");
        histogramDataset.addValue(30, "Series 1", "Category 3");

        // Create histogram (bar chart)
        JFreeChart histogramChart = ChartFactory.createBarChart(
                "Histogram Example",
                "Category",
                "Value",
                histogramDataset
        );

        // Create a dataset for line chart
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
        lineDataset.addValue(10, "Series 1", "Category 1");
        lineDataset.addValue(20, "Series 1", "Category 2");
        lineDataset.addValue(30, "Series 1", "Category 3");

        // Create line chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Line Chart Example",
                "Category",
                "Value",
                lineDataset
        );

        // Create panels to hold the charts
        ChartPanel piePanel = new ChartPanel(pieChart);
        ChartPanel histogramPanel = new ChartPanel(histogramChart);
        ChartPanel linePanel = new ChartPanel(lineChart);

        // Set layout for the frame
        setLayout(new GridLayout(1, 3));

        // Add chart panels to the frame
        add(piePanel);
        add(histogramPanel);
        add(linePanel);

        // Set frame properties
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ChartExample();
    }
}
