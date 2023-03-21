package homework.task1;

import com.opencsv.exceptions.CsvException;
import homework.task1.currency.CurrencyDataManager;
import homework.task1.currency.CurrencyOperator;
import homework.task1.dataReaders.CSVDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, CsvException {
        ArrayList<String> availableCurrencies = new ArrayList<>(Arrays.asList(new String[]{"USD", "TRY", "EUR"}));
        String sourceFolder = "currencies/";
        CurrencyDataManager cd = new CurrencyDataManager(sourceFolder, new CurrencyOperator());
        CSVDataReader reader = new CSVDataReader();
        for (String currency : availableCurrencies) {
            cd.addCurrency(currency, reader);
        }
        Scanner in = new Scanner(System.in);
        String operation = in.nextLine();
        cd.executeQuery(operation);
    }
}