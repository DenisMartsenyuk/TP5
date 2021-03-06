package ru.lab.prack5.entities;

import java.util.List;
import java.util.Set;

public class Statistics {
    private List<Double> selection; //выборка
    private List<Double> variationRange; //вариационный ряд
    private Double minValue; //минимальное значение
    private Double maxValue; //максимальное значение
    private Double sweep; //размах
    private Set<StatisticNodeN> statisticalDistribution; //статистическое распределение
    private Double mathExpectation; //мат. ожидание
    private Double dispersion;
    private Double standardDeviation; //среднеквадратическое отклонение
    private Set<StatisticNodeP> empiricalDistribution; //эмпирическое распределение
    private Set<IntervalNode> intervalDistribution; //интервальный ряд

    public Set<IntervalNode> getIntervalDistribution() {
        return intervalDistribution;
    }

    public void setIntervalDistribution(Set<IntervalNode> intervalDistribution) {
        this.intervalDistribution = intervalDistribution;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public void setDispersion(Double dispersion) {
        this.dispersion = dispersion;
    }

    public List<Double> getSelection() {
        return selection;
    }

    public void setSelection(List<Double> selection) {
        this.selection = selection;
    }

    public List<Double> getVariationRange() {
        return variationRange;
    }

    public void setVariationRange(List<Double> variationRange) {
        this.variationRange = variationRange;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getSweep() {
        return sweep;
    }

    public void setSweep(Double sweep) {
        this.sweep = sweep;
    }

    public Double getMathExpectation() {
        return mathExpectation;
    }

    public void setMathExpectation(Double mathExpectation) {
        this.mathExpectation = mathExpectation;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Set<StatisticNodeN> getStatisticalDistribution() {
        return statisticalDistribution;
    }

    public void setStatisticalDistribution(Set<StatisticNodeN> statisticalDistribution) {
        this.statisticalDistribution = statisticalDistribution;
    }

    public Set<StatisticNodeP> getEmpiricalDistribution() {
        return empiricalDistribution;
    }

    public void setEmpiricalDistribution(Set<StatisticNodeP> empiricalDistribution) {
        this.empiricalDistribution = empiricalDistribution;
    }
}
