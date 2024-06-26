package com.dewitt.petshopapi.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "size",defaultValue = "5") Integer size) {
        return ResponseEntity.ok(productService.paginatedProducts(page, size));
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProduct(@PathVariable String uuid) {
        Optional<Product> product = productService.findById(uuid);
        if(product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateProduct(@PathVariable String uuid, @RequestBody ProductRequest productRequest) {
        Optional<Product> product = productService.findById(uuid);
        if(product.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        productService.update(uuid,productRequest);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/deactivate/{uuid}")
    public ResponseEntity<?> disableProduct(@PathVariable String uuid) {
        productService.deactivate(uuid);
        return ResponseEntity.ok().build();
    }
}
