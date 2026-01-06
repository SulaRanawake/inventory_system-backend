package com.inventory.controller;

import com.inventory.dto.product.ProductRequestDTO;
import com.inventory.dto.product.ProductResponseDTO;
import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.ProductMapper;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO request) {
        Product product = ProductMapper.toEntity(request);
        Product saved = productService.createProduct(product, "system");
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO request
    ) {
        Product product = ProductMapper.toEntity(request);
        Product updated = productService.updateProduct(id, product, "system");
        return ResponseEntity.ok(ProductMapper.toResponse(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return ResponseEntity.ok(ProductMapper.toResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> products = productService.getAllProducts()
                .stream()
                .map(ProductMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id, "system");
        return ResponseEntity.noContent().build();
    }
}
