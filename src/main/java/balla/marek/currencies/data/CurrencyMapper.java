package balla.marek.currencies.data;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyMapper {

    List<Currency> mapCVS(InputStream csvStream) {
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
