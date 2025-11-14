package com.reca.apiRestFull.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public record LatestRatesDTO(
        //Vou ignorar todas menos essa
        Map<String, Double> rates,
        @JsonIgnore
        String disclaimer,
        @JsonIgnore
        String license,
        @JsonIgnore
        String timestamp,
        @JsonIgnore
        String base,
        @JsonIgnore
        boolean isOffline
) {
}
