package homework.task1.currency.operations;

import homework.task1.currency.customExceptions.CurrencyOperationValidationError;
import homework.task1.currency.entities.Currency;
import homework.task1.currency.entities.CurrencyStake;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRateOperation implements CurrencyOperation {
    private final List<String> periods = List.of("week", "tomorrow");
    final int RANGE = 7;
    final int ARGUMENTS_COUNT = 2;

    public boolean matches(String[] args) {

        if (args.length != ARGUMENTS_COUNT) {
            throw new CurrencyOperationValidationError("Неверное количество агрументов для операции");
        }
        String currencyName = args[0];
        String period = args[1];
        Currency c = Currency.fromString(currencyName);
        if (c == null || !c.isAvailable()) {
            throw new CurrencyOperationValidationError("Данная валюта неизвестна / недоступна: " + currencyName);
        }
        if (!periods.contains(period)) {
            throw new CurrencyOperationValidationError("Неверно указан период операции " + period);
        }
        return true;
    }

    @Override
    public List<CurrencyStake> execute(List<CurrencyStake> data, String[] args) {
        String periodName = args[0];
        int period = 0;
        if (periodName.equals("week")) {
            period = 7;
        } else if (periodName.equals("tomorrow")) {
            period = 1;
        }
        List<CurrencyStake> currencyData = new ArrayList<>(data.subList(0, RANGE + 1));
        List<CurrencyStake> resultData = new ArrayList<>();
        LocalDate date = LocalDate.now().plusDays(1);
        for (int i = 0; i < period; i++) {
            BigDecimal avg = BigDecimal.valueOf(0);
            for (int j = 0; j < RANGE; j++) {
                BigDecimal rate = currencyData.get(j).getRate();
                avg = avg.add(rate);
            }
            avg = avg.divide(BigDecimal.valueOf(RANGE), 4, RoundingMode.HALF_UP);
            CurrencyStake stake = new CurrencyStake(currencyData.get(i).getNominal(), date, avg, currencyData.get(i).getName());
            currencyData.add(0, stake);
            resultData.add(stake);
            date = date.plusDays(1);
        }
        return resultData;
    }
}
