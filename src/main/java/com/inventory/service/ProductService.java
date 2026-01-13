package com.inventory.service;

import com.inventory.dto.product.ProductRequestDTO;
import com.inventory.dto.product.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO dto);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);

    void deleteProduct(Long id);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();
}
