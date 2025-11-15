package com.reca.apiRestFull.controller;

import com.reca.apiRestFull.dto.PriceDTO;
import com.reca.apiRestFull.service.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity<Void> createPrice(@RequestBody PriceDTO dto) {
        priceService.createPrice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(priceService.getPriceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPrice(@PathVariable Long id, @RequestBody PriceDTO dto) {
        priceService.putPrice(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePrice(@PathVariable Long id) {
        priceService.removePrice(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
