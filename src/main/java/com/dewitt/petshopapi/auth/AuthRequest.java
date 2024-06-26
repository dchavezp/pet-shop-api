package com.dewitt.petshopapi.auth;

import com.dewitt.petshopapi.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
    private String name;
    private String lastname;
    private UserRole role;
}
