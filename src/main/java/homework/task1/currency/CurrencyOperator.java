package homework.task1.currency;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyOperator {
    public List<CurrencyStake> rateByAverage(List<CurrencyStake> data, int period, int range) {
        List<CurrencyStake> currencyData = data.subList(0, range + 1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<CurrencyStake> resultData = new ArrayList<>();
        LocalDate date = LocalDate.now().plusDays(1);
        for (int i = 0; i < period; i++) {
            BigDecimal avg = BigDecimal.valueOf(0);
            for (int j = 0; j < range; j++) {
                BigDecimal rate = new BigDecimal(currencyData.get(j).getRate().replace(",", "."));
                avg = avg.add(rate);
            }
            avg = avg.divide(BigDecimal.valueOf(range), new MathContext(6));
            CurrencyStake stake = new CurrencyStake(
                    currencyData.get(i).getNominal(),
                    date.format(dateFormatter),
                    avg.toString(),
                    currencyData.get(i).getName());
            currencyData.add(0, stake);
            resultData.add(stake);
            date = date.plusDays(1);
        }
        return resultData;
    }
}
