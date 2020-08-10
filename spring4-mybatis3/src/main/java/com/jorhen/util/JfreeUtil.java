package com.jorhen.util;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class JfreeUtil {
	

    // 折线图
    public static void setJfreeLine(JFreeChart chart) {
        // 处理图形上的乱码
                // 处理主标题的乱码
                chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
                // 处理子标题乱码
                chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
                // 获取图表区域对象
                CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
                // 获取X轴的对象
                CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
                // 获取Y轴的对象
                NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
                // 处理X轴上的乱码
                categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
                // 处理X轴外的乱码
                categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
                // 处理Y轴上的乱码
                numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
                // 处理Y轴外的乱码
                numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
                // 处理Y轴上显示的刻度，以1作为1格
                numberAxis.setAutoTickUnitSelection(false);
                NumberTickUnit unit = new NumberTickUnit(1);
                numberAxis.setTickUnit(unit);
                // 获取绘图区域对象
                LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
                // 在图形上显示数字
                lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                lineAndShapeRenderer.setBaseItemLabelsVisible(true);
                lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
                // 在图形上添加转折点（使用小矩形显示）
                Rectangle shape = new Rectangle(10, 10);
                lineAndShapeRenderer.setSeriesShape(0, shape);
                lineAndShapeRenderer.setSeriesShapesVisible(0, true);
    }
}


