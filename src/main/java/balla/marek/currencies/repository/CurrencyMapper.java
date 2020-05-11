package balla.marek.currencies.repository;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import balla.marek.currencies.utils.CSVParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CurrencyMapper {

    public List<Currency> mapCVS(InputStream csvStream) throws IOException, ParseException {
        CSVParser reader = new CSVParser();
        List<Map<String, String>> records = reader.parse(csvStream);

        Map<String, String> record = records.get(0);
        List<Currency> currencies = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        for (CurrencyType currrency : CurrencyType.values()) {
            currencies.add(new Currency(
                                   currrency,
                                   Double.parseDouble(record.get(currrency.toString())),
                                   dateFormat.parse(record.get("Date"))
                           )
            );
        }
        return currencies;
    }
}
