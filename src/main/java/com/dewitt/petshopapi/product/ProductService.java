package com.dewitt.petshopapi.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void save(ProductRequest request) {
        var product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .imageUrl(request.imageUrl())
                .stock(request.stock())
                .state(ProductState.ACTIVE)
                .build();
        productRepository.save(product);
    }
    public Optional<Product> findById(String uuid) {
        return productRepository.findById(uuid);
    }
    public List<Product> paginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }
    public void update(String uuid, ProductRequest request) {
        var product = productRepository.findById(uuid).orElseThrow();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setImageUrl(request.imageUrl());
        product.setStock(request.stock());
        product.setState(request.state());
        productRepository.save(product);
    }
    public void deactivate(String uuid) {
        var product = productRepository.findById(uuid).orElseThrow();
        product.setState(ProductState.INACTIVE);
        productRepository.save(product);
    }
}
