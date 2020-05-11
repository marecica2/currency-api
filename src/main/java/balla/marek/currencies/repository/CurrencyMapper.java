package balla.marek.currencies.repository;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import balla.marek.currencies.utils.CSVParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyMapper {

    public List<Currency> mapCVS(InputStream csvStream) throws IOException {
        CSVParser reader = new CSVParser();
        List<Map<String, String>> records = reader.parse(csvStream);

        Map<String, String> record = records.get(0);
        List<Currency> currencies = new ArrayList<>();

        for (CurrencyType currrency : CurrencyType.values()) {
            currencies.add(new Currency(
                                   currrency,
                                   Double.parseDouble(record.get(currrency.toString()))
                           )
            );
        }
        return currencies;
    }
}
