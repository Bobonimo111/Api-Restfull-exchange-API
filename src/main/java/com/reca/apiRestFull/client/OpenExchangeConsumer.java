package com.reca.apiRestFull.client;

import com.reca.apiRestFull.dto.CurrenciesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "openExchange",url="https://openexchangerates.org")
public interface OpenExchangeConsumer {

    @GetMapping("api/currencies.json")
    Map<String,String> getOcurrencies(
            @RequestParam(name = "app_id",required = true) String token  ,
            @RequestParam(name = "show_alternative") Boolean show_alternative
    );

}
