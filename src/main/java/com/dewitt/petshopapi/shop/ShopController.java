package com.dewitt.petshopapi.shop;

import com.dewitt.petshopapi.Application;
import com.dewitt.petshopapi.auth.AuthRequest;
import com.dewitt.petshopapi.auth.AuthResponse;
import com.dewitt.petshopapi.auth.AuthService;
import com.dewitt.petshopapi.exceptions.AlreadyRegisteredException;
import com.dewitt.petshopapi.exceptions.NotFoundException;
import com.dewitt.petshopapi.shop.cart.Cart;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final AuthService authService;
    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDTO request) {
        try{
            shopService.addToCart(authService.getUserLogged().getUuid(),request.productId(),request.quantity());
            return ResponseEntity.ok().build();
        } catch (AlreadyRegisteredException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove-from-cart/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable String productId){
        try{
            shopService.removeFromCart(authService.getUserLogged().getUuid(),productId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
