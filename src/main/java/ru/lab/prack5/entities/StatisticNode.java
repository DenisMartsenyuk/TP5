package ru.lab.prack5.entities;

public class StatisticNode implements Comparable <StatisticNode>{
    private Double value;
    private Double probability;

    public StatisticNode(Double value, Double probability) {
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
    public int compareTo(StatisticNode o) {
        return this.value.compareTo(o.value);
    }
}
