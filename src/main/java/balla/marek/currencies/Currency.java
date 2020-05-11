package balla.marek.currencies;

import balla.marek.currencies.rest.CurrencyType;

public class Currency {
    private CurrencyType type;
    private double rate;

    public Currency(CurrencyType type, double rate) {
        this.type = type;
        this.rate = rate;
    }

    public CurrencyType getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "type='" + type + '\'' +
                ", rate=" + rate +
                '}';
    }
}
