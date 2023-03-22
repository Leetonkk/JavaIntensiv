package homework.task1.currency;

import java.util.Arrays;
import java.util.List;

public class CurrencyOperationsMethods {
    public List<CurrencyStake> rate(CurrencyOperator cp, List<CurrencyStake> data, String[] args) {
        List<String> periods = Arrays.asList("tomorrow", "week");
        final int ARGUMENTS_COUNT = 1;
        final int DAYS_IN_WEEK = 7;
        final int DAY = 1;
        List<CurrencyStake> result;
        try {
            if (args.length != ARGUMENTS_COUNT) {
                throw new Exception("Неверное число аргументов для данной операции: " + args.length);
            } else if (!periods.contains(args[0])) {
                throw new Exception("Некорректный период: " + args[0]);
            }
            String period = args[0];
            if (period.equals("week")) {
                result = cp.rateByAverage(data, DAYS_IN_WEEK, DAYS_IN_WEEK);
            } else {
                result = cp.rateByAverage(data, DAY, DAYS_IN_WEEK);
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
