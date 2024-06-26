package com.dewitt.petshopapi.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dewitt.petshopapi.user.Permission.MANAGER_CREATE;
import static com.dewitt.petshopapi.user.Permission.MANAGER_READ;
import static com.dewitt.petshopapi.user.Permission.MANAGER_UPDATE;
import static com.dewitt.petshopapi.user.Permission.MANAGER_DELETE;
import static com.dewitt.petshopapi.user.Permission.CLIENT_CREATE;
import static com.dewitt.petshopapi.user.Permission.CLIENT_READ;
import static com.dewitt.petshopapi.user.Permission.CLIENT_UPDATE;
import static com.dewitt.petshopapi.user.Permission.CLIENT_DELETE;
@Getter
@RequiredArgsConstructor
public enum UserRole {

    CLIENT(Set.of(
                CLIENT_CREATE,
                CLIENT_READ,
                CLIENT_UPDATE,
                CLIENT_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    )

    ;
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}