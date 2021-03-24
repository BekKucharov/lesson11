package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Currency;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getCurrency(){
        return currencyRepository.findAll();
    }
    public Currency getCurrencyById(Integer id){
        Optional<Currency> currencyId = currencyRepository.findById(id);
        if (!currencyId.isPresent()) return new Currency();
        Currency currency= currencyId.get();
        return currency;
    }
    public Result addCurrency(Currency currencyDto){
        boolean exists = currencyRepository.existsByName(currencyDto.getName());
        if (exists) return new Result("This kind of currency is already exists", false);
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currencyRepository.save(currency);
        return new Result("Currency added", true);
    }
    public Result editCurrency(Currency currencyDto, Integer id){
        Optional<Currency> currencyId = currencyRepository.findById(id);
        if (!currencyId.isPresent()) return new Result("Currency not found", false);
        Currency editingCurrency = currencyId.get();
        editingCurrency.setName(currencyDto.getName());
        currencyRepository.save(editingCurrency);
        return new Result("Currency added", true);
    }
    public Result delCurrency(Integer id){
        Optional<Currency> currencyId = currencyRepository.findById(id);
        if (!currencyId.isPresent()) return new Result("Currency not found", false);
        currencyRepository.deleteById(id);
        return new Result("Currency deleted", true);
    }
}
