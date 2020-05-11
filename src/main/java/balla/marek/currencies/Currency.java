package balla.marek.currencies;

import java.util.Date;

public class Currency {
    private CurrencyType type;
    private double rate;
    private Date date = new Date();

    public Currency(CurrencyType type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    public Currency(CurrencyType type, double rate, Date date) {
        this.type = type;
        this.rate = rate;
        this.date = date;
    }

    public CurrencyType getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public Date getDate() {
        return date;
    }
}
