import ru.lab.prack5.entities.Statistics;
import ru.lab.prack5.utils.PainterStatistics;
import ru.lab.prack5.utils.PrinterStatistics;
import ru.lab.prack5.utils.ReaderSelection;
import ru.lab.prack5.utils.StatisticsCreator;

public class Application {
    private ReaderSelection readerSelection;
    private StatisticsCreator statisticsCreator;

    public Application(String[] arguments) {
        if (arguments.length == 0) {
            System.out.println("Недостаточное количество вргументов для приложения.");
            return;
        }
        readerSelection = new ReaderSelection(arguments[0]);
        statisticsCreator = new StatisticsCreator();
    }

    public void start() {
        Statistics statistics = statisticsCreator.getStatistics(readerSelection.readSelection());
        PrinterStatistics.printStatistics(statistics);
        PainterStatistics painterStatistics = new PainterStatistics();
        painterStatistics.drawGraphs(statistics);
    }
}
