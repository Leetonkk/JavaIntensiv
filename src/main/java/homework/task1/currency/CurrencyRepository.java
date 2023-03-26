package homework.task1.currency;

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
            List<CurrencyStake> fileData = reader.readFile(filePath);
            if (fileData != null) {
                data.put(currencyName, fileData);
                currency.setAvailable(true);
            } else {
                System.out.println("Данная валюта не доступна: " + currencyName);
                currency.setAvailable(false);
            }
        }
    }

    public List<CurrencyStake> getCurrencyData(String currencyName) {
        return data.getOrDefault(currencyName, null);
    }
}
