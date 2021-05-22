package ru.lab.prack5.utils;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import ru.lab.prack5.entities.StatisticNode;
import ru.lab.prack5.entities.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PainterStatistics {
    private final XYChart empiricalDistribution;
    private final XYChart poligon;
    private final XYChart histogram;

    public PainterStatistics() {
        empiricalDistribution = new XYChartBuilder().width(600).height(400).title("График эмпирической функции распределения").xAxisTitle("x").yAxisTitle("F(x)").build();
        empiricalDistribution.getStyler().setChartTitleVisible(false);
        empiricalDistribution.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        empiricalDistribution.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        empiricalDistribution.getStyler().setMarkerSize(5);

        poligon = new XYChartBuilder().width(600).height(400).title("Полигон частот").xAxisTitle("x").yAxisTitle("p").build();
        poligon.getStyler().setChartTitleVisible(false);
        poligon.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        poligon.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        poligon.getStyler().setMarkerSize(5);

        histogram = new XYChartBuilder().width(600).height(400).title("Гистограмма частот").xAxisTitle("ч").yAxisTitle("p/h").build();
        histogram.getStyler().setChartTitleVisible(false);
        histogram.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        histogram.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        histogram.getStyler().setMarkerSize(5);
    }

    public void drawGraphs(Statistics statistics) {

        poligon.addSeries("Полигон", getXFromSet(statistics.getStatisticalDistribution()), getYFromSet(statistics.getStatisticalDistribution()));

        openGraphs();
    }

    private List<Double> getXFromSet(Set<StatisticNode> set) {
        ArrayList<Double> pointsX = new ArrayList<>();
        for (StatisticNode statisticNode : set) {
            pointsX.add(statisticNode.getValue());
        }
        return pointsX;
    }

    private List<Double> getYFromSet(Set<StatisticNode> set) {
        ArrayList<Double> pointsY = new ArrayList<>();
        for (StatisticNode statisticNode : set) {
            pointsY.add(statisticNode.getProbability());
        }
        return pointsY;
    }

    private void openGraphs() {
        new SwingWrapper(empiricalDistribution).displayChart();
        new SwingWrapper(poligon).displayChart();
        new SwingWrapper(histogram).displayChart();
    }
}
