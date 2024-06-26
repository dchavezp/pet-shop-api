package com.dewitt.petshopapi.shop.likes;

import com.dewitt.petshopapi.product.Product;
import com.dewitt.petshopapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "_likes")
public class Likes {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;
    @ManyToOne
    @JoinColumn(name="user_uuid",referencedColumnName = "user_uuid")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_uuid",referencedColumnName = "product_uuid")
    private Product product;
    private Date date;

}
