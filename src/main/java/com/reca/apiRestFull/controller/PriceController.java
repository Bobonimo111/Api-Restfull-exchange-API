package com.reca.apiRestFull.controller;

import com.reca.apiRestFull.dto.PriceDTO;
import com.reca.apiRestFull.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity<Void> createPrice(@RequestBody PriceDTO dto) {
        priceService.createPrice(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(priceService.getPriceById(id));
    }

    @GetMapping
    public ResponseEntity<List<PriceDTO>> getAllPricesBySymbol(@RequestParam String symbol) {
        return ResponseEntity.ok(priceService.getAllPricesBySymbol(symbol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPrice(@PathVariable Long id, @RequestBody PriceDTO dto) {
        priceService.putPrice(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePrice(@PathVariable Long id) {
        priceService.removePrice(id);
        return ResponseEntity.noContent().build();
    }
}
