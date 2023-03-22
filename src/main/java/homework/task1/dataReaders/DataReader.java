package homework.task1.dataReaders;

import homework.task1.currency.CurrencyStake;

import java.util.List;

public interface DataReader {
    List<CurrencyStake> readFile(String filePath);
}
