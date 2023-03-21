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
        public List<String[]> call(CurrencyOperator cp, List<String[]> data, String[] args) throws IncorrectQueryException {
            List<String[]> result;
            try {
                checkRateOperation(args);
                String period = args[0];
                if (period.equals("week")) {
                    result = cp.rateByAverage(data, DAYS_IN_WEEK, DAYS_IN_WEEK);
                } else {
                    result = cp.rateByAverage(data, DAY, DAYS_IN_WEEK);
                }
                return result;
            } catch (IncorrectQueryException ex) {
                throw new IncorrectQueryException(ex.getMessage(), ex.getIncorrectArgument());
            }
        }

        public void checkRateOperation(String[] args) throws IncorrectQueryException {
            if (args.length != ARGUMENTS_COUNT) {
                throw new IncorrectQueryException("Неверное число аргументов для данной операции: ", Integer.toString(args.length));
            } else if (!periods.contains(args[0])) {
                throw new IncorrectQueryException("Некорректный период: ", args[0]);
            }

        }
    };

    public abstract List<String[]> call(CurrencyOperator cp, List<String[]> data, String[] arguments) throws IncorrectQueryException;
}
