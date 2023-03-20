package homework.task1.dataReaders;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataReader<E> {
    List<E> readFile(String filePath) throws IOException, CsvException;
}
