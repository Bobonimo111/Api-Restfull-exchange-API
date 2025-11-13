package com.reca.apiRestFull.client;

import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "openExchange",url="https://openexchangerates.org/")
public interface OpenExchangeConsumer {

    // siglas por nomes
    @GetMapping("api/currencies.json")
    Map<String,String> getOcurrencies(
            @RequestParam(name = "app_id",required = true) String token  ,
            @RequestParam(name = "show_alternative",defaultValue = "false") Boolean show_alternative
    );

    //Cotações
    @GetMapping("api/latest.json")
    LatestRatesDTO getLatestRates(
            @RequestParam(name = "app_id",required = true) String token,
            @RequestParam(name = "symbols", required = false) String symbols,
            @RequestParam(name = "base",required = false,defaultValue = "usd") String base
    );

}
