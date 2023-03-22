package homework.task1.currency;

import homework.task1.dataReaders.CsvDataReader;
import homework.task1.dataReaders.DataReader;

import java.lang.reflect.Method;
import java.util.*;

public class CurrencyDataManager {
    private final DataReader reader;
    private final CurrencyOperator currencyOperator = new CurrencyOperator();
    private final CurrencyOperationsMethods currencyOperationsMethods = new CurrencyOperationsMethods();
    private final List<String> availableCurrencies = new ArrayList<>();
    private final HashMap<String, List<CurrencyStake>> data = new HashMap<>();


    public CurrencyDataManager(CsvDataReader reader) {
        this.reader = reader;
    }

    public void start() {
        List<String> currencies = List.of("USD", "TRY", "EUR"); // заданы руками, не знаю куда их засунуть
        // могу предположить что в какие нибудь проперти проекта, как начальные агрументы
        for (String currency : currencies) {
            this.addCurrency("currencies/", currency);
        }
        while (true) {
            Scanner in = new Scanner(System.in);
            String query = in.nextLine();
            if ("stop".equalsIgnoreCase(query)) {
                break;
            }
            this.executeQuery(query);
        }

    }

    public void addCurrency(String folder, String currencyName) {
        String filePath = folder + currencyName;
        if (!data.containsKey(currencyName)) {
            List<CurrencyStake> fileData = reader.readFile(filePath);
            if (fileData != null) {
                availableCurrencies.add(currencyName);
                data.put(currencyName, fileData);
            } else {
                System.out.println("Данная валюта не доступна: " + currencyName);
            }
        }
    }

    public void executeQuery(String query) {

        try {
            String[] queryElements = query.split(" ");
            String operation = queryElements[0];
            String currency = queryElements[1];
            checkCurrencyAvailability(currency);
            Method m = getCurrencyMethod(operation);
            if (m != null) {
                List<CurrencyStake> resultData = (List<CurrencyStake>) m.invoke(
                        currencyOperationsMethods,
                        currencyOperator,
                        data.get(currency),
                        Arrays.copyOfRange(queryElements, 2, queryElements.length
                        ));
                if (resultData != null) {
                    new CurrencyDataFormatter().printDataDefaultFormat(resultData);
                }
            } else {
                throw new Exception("Данная операция не поддерживается: " + operation);
            }

        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Недостаточное количество агрументов");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Method getCurrencyMethod(String operation) {
        Method[] allMethods = CurrencyOperationsMethods.class.getDeclaredMethods();
        for (Method m : allMethods) {
            if (m.getName().equals(operation)) {
                return m;
            }
        }
        return null;
    }

    public void checkCurrencyAvailability(String currency) throws Exception {
        if (!availableCurrencies.contains(currency)) {
            throw new Exception("Данная валюта не найдена: " + currency);
        }
    }
}
