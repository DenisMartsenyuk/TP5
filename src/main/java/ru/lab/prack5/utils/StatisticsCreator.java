package ru.lab.prack5.utils;

import ru.lab.prack5.entities.StatisticNode;
import ru.lab.prack5.entities.Statistics;

import java.util.*;

public class StatisticsCreator {
    public Statistics getStatistics(List<Double> selection) {
        Statistics statistics = new Statistics();
        statistics.setSelection(selection);

        List<Double> variationRange = getVariationRange(selection);
        statistics.setVariationRange(variationRange);
        statistics.setMinValue(variationRange.get(0));
        statistics.setMaxValue(variationRange.get(variationRange.size() - 1));
        statistics.setSweep(statistics.getMaxValue() - statistics.getMinValue()); //todo отрицательный?

        Set<StatisticNode> statisticalDistribution = getStatisticalDistribution(selection);
        statistics.setStatisticalDistribution(statisticalDistribution);
        statistics.setMathExpectation(getMathExpectation(statisticalDistribution));
        statistics.setStandardDeviation(getStandardDeviation(statisticalDistribution));





        return statistics;
    }

    private List<Double> getVariationRange(List<Double> selection) {
        List<Double> variationRange = new ArrayList<> (selection);
        Collections.sort(variationRange);
        return variationRange;
    }

    private Set<StatisticNode> getStatisticalDistribution(List<Double> selection) {
        Set<StatisticNode> statisticalDistribution = new TreeSet<>();
        for (Double node : selection) {
            Integer quantity = 0;
            for (Double comparableNode : selection) {
                if (node.equals(comparableNode)) {
                    quantity++;
                }
            }
            Double probability = quantity * 1.0 / selection.size();
            statisticalDistribution.add(new StatisticNode(node, probability));
        }

        return statisticalDistribution;
    }

    private Double getMathExpectation(Set<StatisticNode> statisticalDistribution) {
        Double mathExpectation = 0.0;
        for (StatisticNode statisticNode : statisticalDistribution) {
            mathExpectation += statisticNode.getValue() * statisticNode.getProbability();
        }
        return mathExpectation;
    }

    private Double getDispersion(Set<StatisticNode> statisticalDistribution) {
        Double mathExpectation = getMathExpectation(statisticalDistribution);
        Double mathExpectationSquare = 0.0;
        for (StatisticNode statisticNode : statisticalDistribution) {
            mathExpectationSquare +=  statisticNode.getValue() * statisticNode.getValue() * statisticNode.getProbability();
        }
        return mathExpectationSquare - mathExpectation * mathExpectation;
    }

    private Double getStandardDeviation(Set<StatisticNode> statisticalDistribution) {
        return Math.sqrt(getDispersion(statisticalDistribution));
    }

    private Set<StatisticNode> getEmpiricalDistribution(Set<StatisticNode> statisticalDistribution) {
        Set<StatisticNode> empiricalDistribution = new TreeSet<>();

        Double probability = 0.0;
        for (StatisticNode statisticNode : statisticalDistribution) {
            empiricalDistribution.add(new StatisticNode(statisticNode.getValue(), probability));
            probability += statisticNode.getProbability();
        } //todo добавить еще
        return empiricalDistribution;
    }
}
