package homework.task1.currency.services;

import homework.task1.currency.customExceptions.CurrencyOperationValidationError;
import homework.task1.currency.entities.Command;
import homework.task1.currency.operations.CurrencyOperation;
import homework.task1.currency.operations.CurrencyOperationFactory;

public class CurrencyOperationProvider {
    private final CurrencyOperationFactory currencyOperationFactory;

    public CurrencyOperationProvider(CurrencyOperationFactory currencyOperationFactory) {
        this.currencyOperationFactory = currencyOperationFactory;
    }

    public CurrencyOperation getOperation(String operationName) {
        if (operationName.length() == 0) {
            throw new CurrencyOperationValidationError("Была введена пустая строка");
        }
        Command command = Command.fromString(operationName);
        if (command == null) {
            throw new CurrencyOperationValidationError("Неизвестная комманда " + operationName);
        }
        return currencyOperationFactory.getOperation(command);

    }
}
