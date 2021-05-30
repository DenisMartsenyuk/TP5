package ru.lab.prack5.entities;

public class StatisticNodeP implements Comparable <StatisticNodeP>{
    private Double value;
    private Double probability;

    public StatisticNodeP(Double value, Double probability) {
        this.value = value;
        this.probability = probability;
    }

    public Double getValue() {
        return value;
    }

    public Double getProbability() {
        return probability;
    }

    @Override
    public int compareTo(StatisticNodeP o) {
        return this.value.compareTo(o.value);
    }
}
