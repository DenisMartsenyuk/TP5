package ru.lab.prack5.utils;

import ru.lab.prack5.entities.StatisticNode;
import ru.lab.prack5.entities.Statistics;

import java.util.List;
import java.util.Set;

public class PrinterStatistics {
    private final static String FORMAT = "%7.2f";

    public static void printStatistics(Statistics statistics) {
        printList(statistics.getSelection(), "Выборка", FORMAT);
        printList(statistics.getVariationRange(), "Вариационный ряд", FORMAT);
        printDoubleField(statistics.getMinValue(), "Минимальное значение", FORMAT);
        printDoubleField(statistics.getMaxValue(), "Максимальное значение", FORMAT);
        printDoubleField(statistics.getSweep(), "Размах", FORMAT);
        printSet(statistics.getStatisticalDistribution(), "Статистическое  распределение", FORMAT);
        printDoubleField(statistics.getMathExpectation(), "Мат. ожидание", FORMAT);
        printDoubleField(statistics.getDispersion(), "Дисперсия", FORMAT);
        printDoubleField(statistics.getStandardDeviation(), "Среднеквадратическое отклонение", FORMAT);
        printSet(statistics.getEmpiricalDistribution(), "Эмпирическое распределение", FORMAT);
    }

    private static void printDoubleField(Double field, String name, String format) {
        System.out.print(name + ": ");
        System.out.printf(format, field);
        System.out.println();
    }

    private static void printList(List<Double> list, String name, String format) {
        System.out.println(name + ": ");
        for (Double element : list) {
            System.out.printf(format, element);
        }
        System.out.println();
    }

    private static void printSet(Set<StatisticNode> set, String name, String format) {
        System.out.println(name + ": ");
        for (StatisticNode statisticNode : set) {
            System.out.printf(format, statisticNode.getValue());
        }
        System.out.println();
        for (StatisticNode statisticNode : set) {
            System.out.printf(format, statisticNode.getProbability());
        }
        System.out.println();
    }
}
