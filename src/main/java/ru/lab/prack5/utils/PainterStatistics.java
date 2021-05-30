package ru.lab.prack5.utils;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import ru.lab.prack5.entities.IntervalNode;
import ru.lab.prack5.entities.StatisticNodeP;
import ru.lab.prack5.entities.Statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PainterStatistics {
    private final XYChart empirical;
    private final XYChart polygon;
    private final XYChart histogram;

    public PainterStatistics() {
        empirical = new XYChartBuilder().width(600).height(400).title("График эмпирической функции распределения").xAxisTitle("x").yAxisTitle("F(x)").build();
        empirical.getStyler().setChartTitleVisible(false);
        empirical.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        empirical.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        empirical.getStyler().setMarkerSize(5);

        polygon = new XYChartBuilder().width(600).height(400).title("Полигон частот").xAxisTitle("x").yAxisTitle("n").build();
        polygon.getStyler().setChartTitleVisible(false);
        polygon.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        polygon.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        polygon.getStyler().setMarkerSize(5);

        histogram = new XYChartBuilder().width(600).height(400).title("Гистограмма частот").xAxisTitle("ч").yAxisTitle("n/h").build();
        histogram.getStyler().setChartTitleVisible(false);
        histogram.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        histogram.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        histogram.getStyler().setMarkerSize(5);
    }

    public void drawGraphs(Statistics statistics) {
        empirical.addSeries("График эмпирической\nфункции распределения", getXEmpirical(statistics.getEmpiricalDistribution()), getYEmpirical(statistics.getEmpiricalDistribution()));
        polygon.addSeries("Полигон частностей", getXPolygon(statistics), getYPolygon(statistics));
        histogram.addSeries("Гистограмма частностей", getXHistogram(statistics), getYHistogram(statistics));
        openGraphs();
    }

    private List<Double> getXEmpirical(Set<StatisticNodeP> empiricalDistribution) {
        ArrayList<Double> pointsX = new ArrayList<>();
        int counter = 1;
        for (StatisticNodeP statisticNodeP : empiricalDistribution) {
            pointsX.add(statisticNodeP.getValue());
            if (counter < empiricalDistribution.size()) {
                pointsX.add(statisticNodeP.getValue());
            }
            counter++;
        }
        return pointsX;
    }

    private List<Double> getYEmpirical(Set<StatisticNodeP> empiricalDistribution) {
        ArrayList<Double> pointsY = new ArrayList<>();
        Iterator<StatisticNodeP> iterator = empiricalDistribution.iterator();
        Double previous = iterator.next().getProbability();
        while (iterator.hasNext()) {
            pointsY.add(previous);
            Double probability = iterator.next().getProbability();
            pointsY.add(probability);
            previous = probability;
        }
        pointsY.add(previous);
        return pointsY;
    }

    private List<Double> getXPolygon(Statistics statistics) {
        ArrayList<Double> pointsX = new ArrayList<>();
        for (IntervalNode intervalNode : statistics.getIntervalDistribution()) {
            pointsX.add((intervalNode.getRight() + intervalNode.getLeft()) / 2);
        }
        return pointsX;
    }

    private List<Double> getYPolygon(Statistics statistics) {
        ArrayList<Double> pointsY = new ArrayList<>();
        for (IntervalNode intervalNode : statistics.getIntervalDistribution()) {
            pointsY.add(Double.valueOf(intervalNode.getQuantity()));
        }
        return pointsY;
    }

    private List<Double> getXHistogram(Statistics statistics) {
        ArrayList<Double> pointsX = new ArrayList<>();
        for (IntervalNode intervalNode : statistics.getIntervalDistribution()) {
            pointsX.add(intervalNode.getLeft());
            pointsX.add(intervalNode.getLeft());
            pointsX.add(intervalNode.getRight());
            pointsX.add(intervalNode.getRight());
        }
        return pointsX;
    }

    private List<Double> getYHistogram(Statistics statistics) {
        ArrayList<Double> pointsY = new ArrayList<>();
        Double h = statistics.getIntervalDistribution().iterator().next().getRight() - statistics.getIntervalDistribution().iterator().next().getLeft();
        for (IntervalNode intervalNode : statistics.getIntervalDistribution()) {
            pointsY.add(0.0);
            pointsY.add(intervalNode.getQuantity() * 1.0 / h);
            pointsY.add(intervalNode.getQuantity() * 1.0 / h);
            pointsY.add(0.0);
        }
        return pointsY;
    }


    private void openGraphs() {
        new SwingWrapper(empirical).displayChart();
        new SwingWrapper(polygon).displayChart();
        new SwingWrapper(histogram).displayChart();
    }
}
