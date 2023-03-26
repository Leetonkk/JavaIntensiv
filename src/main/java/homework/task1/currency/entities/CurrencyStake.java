package homework.task1.currency.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import homework.task1.currency.dataReaders.converters.StringToBigDecimal;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyStake {
    @CsvBindByName
    private String nominal;
    @CsvBindByName(column = "data")
    @CsvDate(value = "dd.MM.yyyy")
    private LocalDate date;
    @CsvCustomBindByName(column = "curs", converter = StringToBigDecimal.class)
    private BigDecimal rate;
    @CsvBindByName(column = "cdx")
    private String name;

    public CurrencyStake() {
    }

    public CurrencyStake(String nominal, LocalDate date, BigDecimal rate, String name) {
        this.nominal = nominal;
        this.date = date;
        this.rate = rate;
        this.name = name;
    }

    public String getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }


    @Override
    public String toString() {
        return "CurrencyStake{" +
                "nominal='" + nominal + '\'' +
                ", date='" + date + '\'' +
                ", rate='" + rate + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
