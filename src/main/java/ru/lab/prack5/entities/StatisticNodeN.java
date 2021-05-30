package ru.lab.prack5.entities;

public class StatisticNodeN implements Comparable <StatisticNodeN>{
    private Double value;
    private Integer quantity;

    public StatisticNodeN(Double value, Integer quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(StatisticNodeN o) {
        return this.value.compareTo(o.value);
    }
}
