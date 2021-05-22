package ru.lab.prack5.utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderSelection {
    private Scanner scanner;

    public ReaderSelection(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            scanner = new Scanner(fileReader);
        } catch (Exception e) {
            System.out.println("Не удалось прочитать файл.");
            System.exit(0);
        }
    }

    public List<Double> readSelection() {
        ArrayList<Double> selection = new ArrayList<>();
        try {
            while (scanner.hasNext()) {
                selection.add(scanner.nextDouble());
            }
        } catch (Exception e) {
            System.out.println("Неверные данные в файле.");
            System.exit(0);
        }
        return selection;
    }
}
