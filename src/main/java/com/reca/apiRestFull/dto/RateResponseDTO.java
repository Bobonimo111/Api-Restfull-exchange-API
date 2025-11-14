package com.reca.apiRestFull.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Builder;

public record RateResponseDTO(
        String moeda,
        Double valor,
        boolean isOffline
) {
}
