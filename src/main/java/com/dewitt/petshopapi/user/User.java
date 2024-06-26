package com.dewitt.petshopapi.user;

import com.dewitt.petshopapi.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "_user",
        uniqueConstraints = @UniqueConstraint(
            name = "unique_email",
            columnNames = "user_email")
)
public class User implements UserDetails {
    @Id
    @Column(name = "user_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(nullable = false,name = "user_email")
    private String email;
    @Column(name = "user_lastname")
    private String lastname;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
