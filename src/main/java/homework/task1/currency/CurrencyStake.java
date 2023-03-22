package homework.task1.currency;

import com.opencsv.bean.CsvBindByName;

public class CurrencyStake {
    @CsvBindByName
    private String nominal;
    @CsvBindByName(column = "data")
    private String date;
    @CsvBindByName(column = "curs")
    private String rate;
    @CsvBindByName(column = "cdx")
    private String name;

    public CurrencyStake() {
    }

    public CurrencyStake(String nominal, String date, String rate, String name) {
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

    public String getDate() {
        return date;
    }

    public String getRate() {
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
