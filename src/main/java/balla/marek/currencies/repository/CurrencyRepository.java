package balla.marek.currencies.repository;

import balla.marek.currencies.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class CurrencyRepository {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyRepository.class);

    private List<Currency> currencies = new ArrayList<>();

    private CurrencyMapper currencyMapper;

    public CurrencyRepository(CurrencyMapper currencyMapper) {
        this.currencyMapper = currencyMapper;
    }

    @PostConstruct
    @Scheduled(cron = "*/10 * * * * *")  // triggers every 10 seconds for demo purposes
    public void initialize() throws IOException, ParseException {
        InputStream csvStream = new ClassPathResource("eurofxref.csv").getInputStream();
        this.currencies = this.currencyMapper.mapCVS(csvStream);
        logger.info(this.currencies.size() + " currencies initialized");
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
