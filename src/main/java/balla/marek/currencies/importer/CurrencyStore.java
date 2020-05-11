package balla.marek.currencies.importer;

import balla.marek.currencies.Currency;
import balla.marek.currencies.rest.CurrencyType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class CurrencyStore {
    private List<Currency> currencies = List.of(
            new Currency(CurrencyType.USD, 1.0545),
            new Currency(CurrencyType.AUD, 1.45)
    );

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
