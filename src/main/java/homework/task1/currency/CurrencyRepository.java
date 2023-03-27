package homework.task1.currency;

import homework.task1.currency.customExceptions.FileIsNotFoundException;
import homework.task1.currency.dataReaders.CsvDataReader;
import homework.task1.currency.entities.Currency;
import homework.task1.currency.entities.CurrencyStake;

import java.util.HashMap;
import java.util.List;

public class CurrencyRepository {
    private final CsvDataReader reader;
    private final HashMap<String, List<CurrencyStake>> data = new HashMap<>();

    public CurrencyRepository(CsvDataReader reader) {
        this.reader = reader;
        initializeData();
    }

    public void initializeData() {
        for (Currency c : Currency.values()) {
            addCurrency("currencies/", c);
        }
    }

    public void addCurrency(String folder, Currency currency) {
        String currencyName = currency.toString();
        String filePath = folder + currencyName;

        if (!data.containsKey(currencyName)) {
            try {
                List<CurrencyStake> fileData = reader.readFile(filePath);
                data.put(currencyName, fileData);
                currency.setAvailable(true);
            } catch (FileIsNotFoundException ex) {
                System.out.println(ex.getMessage() + currencyName);
                currency.setAvailable(false);
            }
        }
    }

    public List<CurrencyStake> getCurrencyData(String currencyName) {
        return data.getOrDefault(currencyName, null);
    }
}
