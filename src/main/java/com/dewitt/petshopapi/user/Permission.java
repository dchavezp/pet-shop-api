package com.dewitt.petshopapi.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Permission {
    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete"),
    CLIENT_DELETE("client:delete"),
    CLIENT_CREATE("client:create"),
    CLIENT_UPDATE("client:update"),
    CLIENT_READ("client:read"),
    ;

    private final String permission;
}
