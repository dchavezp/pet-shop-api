package com.dewitt.petshopapi.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductState {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private final String state;
}
