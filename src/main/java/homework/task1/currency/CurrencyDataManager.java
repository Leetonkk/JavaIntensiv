package homework.task1.currency;

import com.opencsv.exceptions.CsvException;
import homework.task1.customExceptions.IncorrectQueryException;
import homework.task1.dataReaders.DataReader;
import org.apache.commons.lang3.EnumUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CurrencyDataManager {
    private final String sourceFolder;
    private CurrencyOperator currencyOperator;
    private ArrayList<String> availableCurrencies = new ArrayList<>();
    private HashMap<String, List<String[]>> data = new HashMap<>();

    public CurrencyDataManager(String sourceFolder, CurrencyOperator currencyOperator) {
        this.sourceFolder = sourceFolder;
        this.currencyOperator = currencyOperator;
    }

    public void addCurrency(String currencyName, DataReader reader) throws IOException, CsvException {
        String filePath = sourceFolder + currencyName;
        if (!this.data.containsKey(currencyName)) {
            List<String[]> fileData = null;
            try {
                fileData = reader.readFile(filePath);
                availableCurrencies.add(currencyName);
                this.data.put(currencyName, fileData);
            } catch (NullPointerException e) {
                System.out.println("Валюта " + currencyName + " не будет доступна");
            }


        }
    }

    public void executeQuery(String query) {
        String[] queryElements = query.split(" ");
        String operation = queryElements[0];
        String currency = queryElements[1];
        try {
            checkOperationAvailability(operation);
            checkCurrencyAvailability(currency);
            List<String[]> resultData = CurrencyOperationsMethods.valueOf(operation).call(
                    currencyOperator,
                    this.data.get(currency),
                    Arrays.copyOfRange(queryElements, 2, queryElements.length
                    ));

            CurrencyDataFormatter dataFormatter = new CurrencyDataFormatter();
            dataFormatter.printDataDefaultFormat(resultData);

        } catch (IncorrectQueryException ex) {
            System.out.println(ex.getMessage() + ex.getIncorrectArgument());
        }

    }

    public void checkOperationAvailability(String operation) throws IncorrectQueryException {
        if (!EnumUtils.isValidEnum(CurrencyOperationsMethods.class, operation)) {
            throw new IncorrectQueryException("Данная операция не поддерживается: ", operation);
        }
    }

    public void checkCurrencyAvailability(String currency) throws IncorrectQueryException {
        if (!availableCurrencies.contains(currency)) {
            throw new IncorrectQueryException("Данная валюта не найдена: ", currency);
        }
    }
}
