package homework.task1.currency;

import homework.task1.customExceptions.IncorrectQueryException;

import java.util.Arrays;
import java.util.List;

public enum CurrencyOperationsMethods {
    rate {
        private List<String> periods = Arrays.asList("tomorrow", "week");
        private final int ARGUMENTS_COUNT = 1;
        private final int DAYS_IN_WEEK = 7;
        private final int DAY = 1;

        @Override
        public List<String[]> call(CurrencyOperator cp, List<String[]> data, String[] args) {
            try {
                checkRateOperation(args);
                String period = args[0];
                List<String[]> result;
                if (period.equals("week")) {
                    result = cp.rateByAverage(data, DAYS_IN_WEEK, DAYS_IN_WEEK);
                } else {
                    result = cp.rateByAverage(data, DAY, DAYS_IN_WEEK);
                }
                return result;
            } catch (IncorrectQueryException ex) {
                System.out.println(ex.getMessage() + ex.getIncorrectArgument());
                return data;
            }
        }

        public void checkRateOperation(String[] args) throws IncorrectQueryException {
            String period = args[0];
            if (args.length != ARGUMENTS_COUNT) {
                throw new IncorrectQueryException("Неверное число аргументов для данной операции: ", Integer.toString(args.length));
            } else if (!periods.contains(period)) {
                throw new IncorrectQueryException("Некорректный период: ", period);
            }

        }
    };

    public abstract List<String[]> call(CurrencyOperator cp, List<String[]> data, String[] arguments);
}
