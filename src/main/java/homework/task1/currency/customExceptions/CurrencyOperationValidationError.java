package homework.task1.currency.customExceptions;

public class CurrencyOperationValidationError extends RuntimeException {
    public CurrencyOperationValidationError(String message){
        super(message);
    }
}
