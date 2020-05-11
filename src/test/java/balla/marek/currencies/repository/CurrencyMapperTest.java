package balla.marek.currencies.repository;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CurrencyMapperTest {

    @Test
    public void shouldMapCurrency() throws IOException, ParseException {
        InputStream csv = new ClassPathResource("eurofxref.csv").getInputStream();
        CurrencyMapper mapper = new CurrencyMapper();
        List<Currency> currencies = mapper.mapCVS(csv);
        Assert.assertEquals(3, currencies.size());
        Assert.assertEquals(CurrencyType.USD, currencies.get(0).getType());
        Assert.assertEquals(new GregorianCalendar(2020, Calendar.MAY, 8).getTime(), currencies.get(0).getDate());
    }

}
