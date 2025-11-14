package com.reca.apiRestFull.dto;

import java.util.Map;

public record CurrenciesDTO(
        Map<String,String> currencies,
        boolean isOffline
        ) {
}
