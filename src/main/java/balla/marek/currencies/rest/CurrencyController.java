package balla.marek.currencies.rest;

import balla.marek.currencies.Currency;
import balla.marek.currencies.importer.CurrencyStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyStore store;

    public CurrencyController(CurrencyStore store) {
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
