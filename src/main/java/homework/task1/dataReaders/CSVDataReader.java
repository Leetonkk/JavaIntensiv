package homework.task1.dataReaders;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import homework.task1.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader implements DataReader<String[]> {
    private final String format = ".csv";
    @Override
    public List<String[]> readFile(String filePath) throws IOException, CsvException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(filePath + format);
        List<String[]> allData = new CSVReader(new InputStreamReader(is), ';').readAll();
        return allData;
    }
}

