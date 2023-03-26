package homework.task1.currency.services;

import homework.task1.currency.CurrencyDataFormatter;
import homework.task1.currency.CurrencyRepository;
import homework.task1.currency.customExceptions.CurrencyOperationValidationError;
import homework.task1.currency.entities.CurrencyStake;
import homework.task1.currency.operations.CurrencyOperation;

import java.util.Arrays;
import java.util.List;

public class CurrencyOperationsExecutorService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyOperationProvider currencyOperationProvider;
    private final CurrencyDataFormatter currencyDataFormatter;

    public CurrencyOperationsExecutorService(CurrencyRepository currencyRepository, CurrencyOperationProvider parser, CurrencyDataFormatter currencyDataFormatter) {
        this.currencyRepository = currencyRepository;
        this.currencyOperationProvider = parser;
        this.currencyDataFormatter = currencyDataFormatter;
    }

    public void executeOperation(String query) {
        try {
            String[] queryParts = query.split(" ");
            String operationName = queryParts[0];
            String[] operationArguments = Arrays.copyOfRange(queryParts, 1, queryParts.length);
            CurrencyOperation operation = currencyOperationProvider.getOperation(operationName);
            if (operation.matches(operationArguments)) {
                List<CurrencyStake> result = operation.execute(currencyRepository.getCurrencyData(operationArguments[0]),
                        Arrays.copyOfRange(operationArguments, 1, operationArguments.length));
                currencyDataFormatter.printDataDefaultFormat(result);
            }

        } catch (CurrencyOperationValidationError ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

}
