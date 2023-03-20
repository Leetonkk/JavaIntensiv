package homework.task1.currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static homework.task1.currency.CurrencyUtils.getDayOfWeek;

public class CurrencyDataFormatter {
    public void printDataDefaultFormat(List<String[]> data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (String[] row : data) {
            LocalDate parsedDate = LocalDate.parse(row[0], formatter);
            System.out.println(getDayOfWeek(parsedDate) + " " + row[0] + " - " + row[1]);
        }
    }
}
