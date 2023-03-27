package homework.task1.currency.dataReaders;

import com.opencsv.bean.CsvToBeanBuilder;
import homework.task1.Main;
import homework.task1.currency.customExceptions.FileIsNotFoundException;
import homework.task1.currency.entities.CurrencyStake;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvDataReader {
    private final static String FILE_EXT = ".csv";

    public List<CurrencyStake> readFile(String filePath) {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(filePath + FILE_EXT);
        if (is == null) {
            throw new FileIsNotFoundException("Данная валюта недоступна: ");
        }
        return new CsvToBeanBuilder<CurrencyStake>(new BufferedReader(new InputStreamReader(is)))
                .withType(CurrencyStake.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();
    }
}

