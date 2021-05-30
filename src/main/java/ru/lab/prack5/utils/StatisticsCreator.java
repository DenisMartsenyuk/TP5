package ru.lab.prack5.utils;

import ru.lab.prack5.entities.IntervalNode;
import ru.lab.prack5.entities.StatisticNodeN;
import ru.lab.prack5.entities.StatisticNodeP;
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
        statistics.setSweep(statistics.getMaxValue() - statistics.getMinValue());

        Set<StatisticNodeN> statisticalDistribution = getStatisticalDistribution(selection);
        statistics.setStatisticalDistribution(statisticalDistribution);
        statistics.setMathExpectation(getMathExpectation(statisticalDistribution, selection.size()));
        statistics.setDispersion(getDispersion(statisticalDistribution, selection.size()));
        statistics.setStandardDeviation(getStandardDeviation(statisticalDistribution, selection.size()));
        statistics.setEmpiricalDistribution(getEmpiricalDistribution(statisticalDistribution, selection.size()));
        statistics.setIntervalDistribution(getIntervalDistribution(statisticalDistribution, statistics.getSweep()));

        return statistics;
    }

    private Set<IntervalNode>  getIntervalDistribution(Set<StatisticNodeN> statisticalDistribution, Double sweep) {
        Set<IntervalNode> intervalDistribution = new TreeSet<>();
        Double h = sweep / Math.round(1 + Math.log10(statisticalDistribution.size()) / Math.log10(2.0));
        Double startInterval = statisticalDistribution.iterator().next().getValue() - h / 2;
        Integer quantity = 0;
        for (StatisticNodeN statisticNodeN : statisticalDistribution) {
            if (statisticNodeN.getValue() - startInterval >= h) {
                IntervalNode intervalNode = new IntervalNode();
                intervalNode.setLeft(startInterval);
                intervalNode.setRight(startInterval + h);
                intervalNode.setQuantity(quantity);
                intervalDistribution.add(intervalNode);
                quantity = statisticNodeN.getQuantity();
                startInterval = startInterval + h;
            } else {
                quantity += statisticNodeN.getQuantity();
            }
        }
        IntervalNode intervalNode = new IntervalNode();
        intervalNode.setLeft(startInterval);
        intervalNode.setRight(startInterval + h);
        intervalNode.setQuantity(quantity);
        intervalDistribution.add(intervalNode);
        return intervalDistribution;
    }

    private List<Double> getVariationRange(List<Double> selection) {
        List<Double> variationRange = new ArrayList<> (selection);
        Collections.sort(variationRange);
        return variationRange;
    }

    private Set<StatisticNodeN> getStatisticalDistribution(List<Double> selection) {
        Set<StatisticNodeN> statisticalDistribution = new TreeSet<>();
        for (Double node : selection) {
            Integer quantity = 0;
            for (Double comparableNode : selection) {
                if (node.equals(comparableNode)) {
                    quantity++;
                }
            }
            statisticalDistribution.add(new StatisticNodeN(node, quantity));
        }

        return statisticalDistribution;
    }

    private Double getMathExpectation(Set<StatisticNodeN> statisticalDistribution, Integer selectionSize) {
        Double mathExpectation = 0.0;
        for (StatisticNodeN statisticNodeN : statisticalDistribution) {
            mathExpectation += statisticNodeN.getValue() * (statisticNodeN.getQuantity() * 1.0 / selectionSize);
        }
        return mathExpectation;
    }

    private Double getDispersion(Set<StatisticNodeN> statisticalDistribution, Integer selectionSize) {
        Double mathExpectation = getMathExpectation(statisticalDistribution, selectionSize);
        Double mathExpectationSquare = 0.0;
        for (StatisticNodeN statisticNodeN : statisticalDistribution) {
            mathExpectationSquare +=  statisticNodeN.getValue() * statisticNodeN.getValue() * (statisticNodeN.getQuantity() * 1.0 / selectionSize);
        }
        return mathExpectationSquare - mathExpectation * mathExpectation;
    }

    private Double getStandardDeviation(Set<StatisticNodeN> statisticalDistribution, Integer selectionSize) {
        return Math.sqrt(getDispersion(statisticalDistribution, selectionSize));
    }

    private Set<StatisticNodeP> getEmpiricalDistribution(Set<StatisticNodeN> statisticalDistribution, Integer selectionSize) {
        Set<StatisticNodeP> empiricalDistribution = new TreeSet<>();

        Integer quantity = 0;
        Double previous = 0.0;
        Double step = 0.0;
        boolean flag = true;
        for (StatisticNodeN statisticNodeN : statisticalDistribution) {
            empiricalDistribution.add(new StatisticNodeP(statisticNodeN.getValue(), quantity * 1.0 / selectionSize));
            quantity += statisticNodeN.getQuantity();

            if (flag) {
                previous = statisticNodeN.getValue();
                flag = false;
                continue;
            }
            if (Math.abs(previous - statisticNodeN.getValue()) > step) {
                step = Math.abs(previous - statisticNodeN.getValue());
            }
            previous = statisticNodeN.getValue();
        }
        empiricalDistribution.add(new StatisticNodeP(previous + step, quantity * 1.0 / selectionSize));
        return empiricalDistribution;
    }
}
