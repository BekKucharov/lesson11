package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Currency;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> getCurrency(){
        return currencyService.getCurrency();
    }
    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }
    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        return currencyService.addCurrency(currency);
    }
    @PutMapping("/{id}")
    public Result editCurrency(@RequestBody Currency currency, @PathVariable Integer id){
        return currencyService.editCurrency(currency, id);
    }
    @DeleteMapping("/{id}")
    public Result delCurrency(@PathVariable Integer id){
        return currencyService.delCurrency(id);
    }

}
