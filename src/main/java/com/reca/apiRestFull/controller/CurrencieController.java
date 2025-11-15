package com.reca.apiRestFull.controller;

import com.reca.apiRestFull.dto.CurrencieDTO;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.service.CurrencieService;
import org.hibernate.Remove;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/currencies")
public class CurrencieController {

    private final CurrencieService currencieService;

    public CurrencieController(CurrencieService currencieService) {
        this.currencieService = currencieService;
    }

    @GetMapping
    public ResponseEntity<CurrenciesDTO> getAllCurrencie(){
        return ResponseEntity.status(HttpStatus.OK).body(currencieService.getAllCurrencies());
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<CurrencieDTO> getCurrency(@PathVariable(name = "symbol") String symbol){
        return ResponseEntity.status(HttpStatus.OK).body(currencieService.getBySymbol(symbol.toUpperCase()));
    }

    @PostMapping
    public ResponseEntity<Void> getCurrencie(@RequestBody CurrencieDTO dto){
        currencieService.CreateNewCurrencie(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{symbol}")
    public ResponseEntity<Void> deleteCurrencie(@PathVariable String symbol){
        currencieService.removeCurrencieBySymbol(symbol);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putCurrencie(@PathVariable Long id, @RequestBody CurrencieDTO dto){
        currencieService.updateCurrencie(id,dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
