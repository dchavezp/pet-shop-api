package com.dewitt.petshopapi.shop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CartDTO(
        @Min(0)
        int quantity,
        @NotEmpty()
        String productId) {
}
