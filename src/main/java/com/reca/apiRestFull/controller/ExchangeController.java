package com.reca.apiRestFull.controller;

import com.reca.apiRestFull.dto.ConversionRequestDTO;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import com.reca.apiRestFull.dto.RateResponseDTO;
import com.reca.apiRestFull.service.ExchangeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(("api/miscs"))
public class ExchangeController {

    private final ExchangeService exchangeService;


    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("latest")
    public ResponseEntity<LatestRatesDTO> getLatestRates
            (@RequestParam(name = "symbols", required = false,defaultValue = "") String symbols,
             @RequestParam(name = "base",required = false,defaultValue = "usd") String base
            ){
        return ResponseEntity.ok().body(exchangeService.getLatestRates(symbols,base));
    }

    @GetMapping("currencies")
    public ResponseEntity<Map<String,String>> getCurrencies(
            @RequestParam(name = "show_alternative",defaultValue = "False") boolean show_alternative){
        return ResponseEntity.ok().body(exchangeService.getCurrencies(show_alternative));
    }

    @GetMapping("teapot")
    public ResponseEntity<String> getTeapotStatus() {
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body("O servidor é um bule de chá e se recusa a preparar café! 🫖");
    }

    //    @GetMapping("rate-by-name")
//    public ResponseEntity<?> getRateByName(
//            @RequestParam(name = "name") String name
//    ) {
//        try {
//            Double rate = exchangeService.getRateByCurrencyName(name);
//            RateResponseDTO responseBody = new RateResponseDTO(name, rate,false);
//            return ResponseEntity.ok(responseBody);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("rate-by-code")
//    public ResponseEntity<?> getRateByCode(
//            @RequestParam(name = "code") String code
//    ) {
//        try {
//            Double rate = exchangeService.getRateBySymbol(code);
//            RateResponseDTO responseBody = new RateResponseDTO(code, rate,false);
//            return ResponseEntity.ok(responseBody);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("convert-to-usd")
//    public ResponseEntity<?> convertToUsd(
//            @RequestBody ConversionRequestDTO request
//    ) {
//        try {
//            Double usdValue = exchangeService.convertToUsd(request.code(), request.value());
//            RateResponseDTO response = new RateResponseDTO("USD", usdValue,false);
//            return ResponseEntity.ok(response);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}