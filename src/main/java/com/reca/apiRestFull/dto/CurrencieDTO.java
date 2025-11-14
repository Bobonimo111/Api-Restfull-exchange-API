package com.reca.apiRestFull.dto;

import jakarta.validation.constraints.NotNull;

public record CurrencieDTO(
        @NotNull
        String symbol,
        @NotNull
        String name
){
}
