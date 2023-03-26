package homework.task1;

import homework.task1.currency.CurrencyDataFormatter;
import homework.task1.currency.CurrencyRepository;
import homework.task1.currency.controllers.ConsoleController;
import homework.task1.currency.dataReaders.CsvDataReader;
import homework.task1.currency.operations.CurrencyOperationFactory;
import homework.task1.currency.services.CurrencyOperationProvider;
import homework.task1.currency.services.CurrencyOperationsExecutorService;

public class Main {


    public static void main(String[] args) {
        new ConsoleController(new CurrencyOperationsExecutorService(
                new CurrencyRepository(new CsvDataReader()),
                new CurrencyOperationProvider(new CurrencyOperationFactory()),
                new CurrencyDataFormatter())
        ).start();
    }
}