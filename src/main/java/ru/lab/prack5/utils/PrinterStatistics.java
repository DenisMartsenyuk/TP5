package ru.lab.prack5.utils;

import ru.lab.prack5.entities.IntervalNode;
import ru.lab.prack5.entities.StatisticNodeN;
import ru.lab.prack5.entities.StatisticNodeP;
import ru.lab.prack5.entities.Statistics;

import java.util.List;
import java.util.Set;

public class PrinterStatistics {
    private final static String FORMAT_DOUBLE = "%7.2f";
    private final static String FORMAT_INT = "%7d";

    public static void printStatistics(Statistics statistics) {
        printList(statistics.getSelection(), "Выборка", "x");
        printList(statistics.getVariationRange(), "Вариационный ряд", "x");
        printDoubleField(statistics.getMinValue(), "Минимальное значение");
        printDoubleField(statistics.getMaxValue(), "Максимальное значение");
        printDoubleField(statistics.getSweep(), "Размах");
        printStatisticNodesN(statistics.getStatisticalDistribution(), "Статистическое  распределение");
        printDoubleField(statistics.getMathExpectation(), "Мат. ожидание");
        printDoubleField(statistics.getDispersion(), "Дисперсия");
        printDoubleField(statistics.getStandardDeviation(), "Среднеквадратическое отклонение");
        printStatisticNodesP(statistics.getEmpiricalDistribution(), "Эмпирическое распределение");
        printIntervalNodes(statistics.getIntervalDistribution(), "Интервальный ряд частностей");
    }

    private static void printIntervalNodes(Set<IntervalNode> intervalNodes, String name) {
        System.out.println(name + ": ");
        System.out.print("l |");
        for (IntervalNode intervalNode : intervalNodes) {
            System.out.printf(FORMAT_DOUBLE, intervalNode.getLeft());
        }
        System.out.println();
        System.out.print("r |");
        for (IntervalNode intervalNode : intervalNodes) {
            System.out.printf(FORMAT_DOUBLE, intervalNode.getRight());
        }
        System.out.println();
        System.out.print("n |");
        for (IntervalNode intervalNode : intervalNodes) {
            System.out.printf(FORMAT_INT, intervalNode.getQuantity());
        }
    }

    private static void printDoubleField(Double field, String name) {
        System.out.print(name + ": ");
        System.out.printf(FORMAT_DOUBLE, field);
        System.out.println();
    }

    private static void printList(List<Double> list, String name, String title) {
        System.out.println(name + ": ");
        System.out.print(title + " |");
        for (Double element : list) {
            System.out.printf(FORMAT_DOUBLE, element);
        }
        System.out.println();
    }

    private static void printStatisticNodesN(Set<StatisticNodeN> set, String name) {
        System.out.println(name + ": ");
        System.out.print("x |");
        for (StatisticNodeN statisticNodeN : set) {
            System.out.printf(FORMAT_DOUBLE, statisticNodeN.getValue());
        }
        System.out.println();
        System.out.print("n |");
        for (StatisticNodeN statisticNodeN : set) {
            System.out.printf(FORMAT_INT, statisticNodeN.getQuantity());
        }
        System.out.println();
    }

    private static void printStatisticNodesP(Set<StatisticNodeP> set, String name) {
        System.out.println(name + ": ");
        System.out.print("x |");
        for (StatisticNodeP statisticNodeP : set) {
            System.out.printf(FORMAT_DOUBLE, statisticNodeP.getValue());
        }
        System.out.println();
        System.out.print("p |");
        for (StatisticNodeP statisticNodeP : set) {
            System.out.printf(FORMAT_DOUBLE, statisticNodeP.getProbability());
        }
        System.out.println();
    }
}
