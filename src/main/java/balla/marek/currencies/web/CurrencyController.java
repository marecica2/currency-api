package balla.marek.currencies.web;

import balla.marek.currencies.Currency;
import balla.marek.currencies.CurrencyType;
import balla.marek.currencies.data.CurrencyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyRepository store;

    public CurrencyController(CurrencyRepository store) {
        this.store = store;
    }

    @GetMapping
    public List<Currency> listAllCurrencies() {
        return this.store.getCurrencies();
    }

    @GetMapping("/{currency}")
    public Optional<Currency> listCurrency(@PathVariable("currency") CurrencyType curreEnum) {
        return this.store
                .getCurrencies()
                .stream()
                .filter(c ->  curreEnum.equals(c.getType()))
                .findFirst();
    }
}
