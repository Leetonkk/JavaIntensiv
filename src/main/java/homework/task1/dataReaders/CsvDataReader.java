package homework.task1.dataReaders;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import homework.task1.Main;
import homework.task1.currency.CurrencyStake;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvDataReader implements DataReader {
    private final static String FILE_EXT = ".csv";

    @Override
    public List<CurrencyStake> readFile(String filePath) {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(filePath + FILE_EXT);
        if (is == null) {
            return null;
        }
        CsvToBean csvToBean = new CsvToBeanBuilder(new BufferedReader(new InputStreamReader(is)))
                .withType(CurrencyStake.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return (List<CurrencyStake>) csvToBean.parse();
    }
}

