package ru.lab.prack5.entities;

public class IntervalNode implements Comparable <IntervalNode> {
    private Double left;
    private Double right;
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(IntervalNode o) {
        return this.right.compareTo(o.right);
    }
}
