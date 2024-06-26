package com.dewitt.petshopapi.shop.cart;

import com.dewitt.petshopapi.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = """
      select c from Cart c where c.user.uuid = ?1 and c.product.uuid = ?2
      """)
    Optional<Cart> findProductAddedInCart(String uuid, String productUuid);
}
