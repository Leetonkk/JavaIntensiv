package homework.task1.dataReaders;

import com.opencsv.exceptions.CsvException;
import homework.task1.currency.CurrencyStake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataReader {
    List<CurrencyStake> readFile(String filePath);
}
