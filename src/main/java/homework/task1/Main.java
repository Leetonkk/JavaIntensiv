package homework.task1;

import homework.task1.currency.CurrencyDataManager;
import homework.task1.currency.CurrencyOperator;
import homework.task1.dataReaders.CsvDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        new CurrencyDataManager(new CsvDataReader()).start();
    }
}