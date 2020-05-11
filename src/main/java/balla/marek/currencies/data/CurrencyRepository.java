package balla.marek.currencies.data;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class CurrencyRepository {

    private List<Currency> currencies = new ArrayList<>();

    private CurrencyMapper currencyMapper;

    public CurrencyRepository(CurrencyMapper currencyMapper) {
        this.currencyMapper = currencyMapper;
    }

    @PostConstruct
    public void initialize() throws IOException {
        InputStream csvStream = new ClassPathResource("eurofxref.csv").getInputStream();
        this.currencies = this.currencyMapper.mapCVS(csvStream);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
