package com.dewitt.petshopapi.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "_product")
public class Product {
    @Id
    @Column(name = "product_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_price")
    private BigDecimal price;
    @Column(name = "product_image_url")
    private String imageUrl;
    @Column(name = "product_stock")
    private Integer stock;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_state")
    private ProductState state;
}
