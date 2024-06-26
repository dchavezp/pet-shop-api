package com.dewitt.petshopapi.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public record ProductRequest(
        @NotEmpty
        String name,
        String description,
        @Min(0)
        BigDecimal price,
        String imageUrl,
        @Min(0)
        Integer stock,
        ProductState state
) {
}