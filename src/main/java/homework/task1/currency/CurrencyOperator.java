package homework.task1.currency;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyOperator {
    private final String CURS_HEADER = "curs";
    private final String DATE_HEADER = "data";

    public List<String[]> rateByAverage(List<String[]> data, int period, int range) {
        List<String[]> currencyData = data.subList(0, range + 1);
        List<String[]> filterData = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DecimalFormat currencyFormatter = new DecimalFormat("#0.0000");
        String[] headers = currencyData.remove(0);
        int dateIndex = Arrays.asList(headers).indexOf(DATE_HEADER);
        int cursIndex = Arrays.asList(headers).indexOf(CURS_HEADER);
        for (int i = 0; i < currencyData.size(); i++) {
            filterData.add(new String[]{currencyData.get(i)[dateIndex], currencyData.get(i)[cursIndex]});
        }
        List<String[]> resultData = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < period; i++) {
            String[] row = new String[2];
            double avg = 0;
            for (int j = 0; j < range; j++) {
                avg += Double.parseDouble(filterData.get(j)[1].replace(",", "."));
            }
            row[0] = date.format(dateFormatter);
            row[1] = currencyFormatter.format(avg / range).replace(".", ",");
            filterData.set(0, row);
            resultData.add(row);
            date = date.plusDays(1);
        }
        return resultData;
    }
}
