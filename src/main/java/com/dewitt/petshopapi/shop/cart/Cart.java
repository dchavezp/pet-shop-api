package com.dewitt.petshopapi.shop.cart;

import com.dewitt.petshopapi.product.Product;
import com.dewitt.petshopapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "_cart")
public class Cart {
    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name="user_uuid",referencedColumnName = "user_uuid")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_uuid",referencedColumnName = "product_uuid")
    private Product product;
    private int quantity;
    private Date dateAdded;
}
