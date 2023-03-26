package homework.task1.currency.operations;

import homework.task1.currency.entities.CurrencyStake;

import java.util.List;

public interface CurrencyOperation {

    List<CurrencyStake> execute(List<CurrencyStake> data, String[] args);

    boolean matches(String[] operationArguments);
}
