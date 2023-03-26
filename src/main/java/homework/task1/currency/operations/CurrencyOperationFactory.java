package homework.task1.currency.operations;

import homework.task1.currency.entities.Command;

public class CurrencyOperationFactory {

    public CurrencyOperation getOperation(Command type) {
        return switch (type) {
            case RATE -> new CurrencyRateOperation();
            default -> throw new IllegalArgumentException("Wrong command type:" + type);
        };
    }
}
