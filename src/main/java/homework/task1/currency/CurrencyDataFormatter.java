package homework.task1.currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static homework.task1.currency.CurrencyUtils.getDayOfWeek;

public class CurrencyDataFormatter {
    public void printDataDefaultFormat(List<CurrencyStake> data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (CurrencyStake stake : data) {
            LocalDate parsedDate = LocalDate.parse(stake.getDate(), formatter);
            System.out.println(getDayOfWeek(parsedDate) + " " + stake.getDate() + " - " + stake.getRate());
        }
    }
}
