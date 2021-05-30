package ru.lab.prack5.entities;

public class IntervalNode implements Comparable <IntervalNode> {
    private Double left;
    private Double right;
    private Double probability;

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo(IntervalNode o) {
        return this.right.compareTo(o.right);
    }
}
