package com.reca.apiRestFull.controller;

import com.reca.apiRestFull.client.OpenExchangeConsumer;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import com.reca.apiRestFull.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RestController("/")
public class ExchangeController {

    private final ExchangeService exchangeService;


    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("latest")
    public ResponseEntity<LatestRatesDTO> getLatestRates(){
        return ResponseEntity.ok().body(exchangeService.getLatestRates());
    }

    @GetMapping("currencies")
    public ResponseEntity<Map<String,String>> getCurrencies(@RequestParam(name = "show_alternative") boolean show_alternative){
        return ResponseEntity.ok().body(exchangeService.getCurrencies(show_alternative));
    }

}
