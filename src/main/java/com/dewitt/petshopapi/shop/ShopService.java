package com.dewitt.petshopapi.shop;

import com.dewitt.petshopapi.exceptions.AlreadyRegisteredException;
import com.dewitt.petshopapi.exceptions.NotFoundException;
import com.dewitt.petshopapi.product.ProductRepository;
import com.dewitt.petshopapi.shop.cart.Cart;
import com.dewitt.petshopapi.shop.cart.CartRepository;
import com.dewitt.petshopapi.shop.likes.LikesRepository;
import com.dewitt.petshopapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final CartRepository shopCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LikesRepository likesRepository;

    public void addToCart(String userId, String productId, int quantity) throws AlreadyRegisteredException {
        var isProductAdded = shopCartRepository.findProductAddedInCart(userId, productId);
        if (isProductAdded.isPresent()) {
            throw new AlreadyRegisteredException();
        }
        var product = productRepository.findById(productId).orElseThrow(AlreadyRegisteredException::new);
        var user = userRepository.findById(userId).orElseThrow();
        var cart = Cart.builder().product(product).user(user).quantity(quantity).build();
        shopCartRepository.save(cart);
    }
    public void removeFromCart(String userId,String productId) throws NotFoundException {
        var cart = shopCartRepository.findProductAddedInCart(userId, productId).orElseThrow(NotFoundException::new);
        shopCartRepository.delete(cart);
    }
}
