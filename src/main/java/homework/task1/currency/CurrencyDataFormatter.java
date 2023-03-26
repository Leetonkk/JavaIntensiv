package homework.task1.currency;

import homework.task1.currency.entities.CurrencyStake;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static homework.task1.currency.utils.CurrencyUtils.getDayOfWeek;

public class CurrencyDataFormatter {
    public void printDataDefaultFormat(List<CurrencyStake> data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (CurrencyStake stake : data) {
            System.out.println(getDayOfWeek(stake.getDate()) + " " + stake.getDate().format(formatter) + " - " + stake.getRate());
        }
    }
}
