package com.inventory.service;

import com.inventory.dto.product.ProductRequestDTO;
import com.inventory.dto.product.ProductResponseDTO;
import com.inventory.enums.Enums.AuditAction;
import com.inventory.entity.Product;
import com.inventory.entity.User;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.ProductMapper;
import com.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuditLogService auditLogService;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        product.setCreatedBy(getCurrentUserId());

        Product saved = productRepository.save(product);

        auditLogService.log(AuditAction.CREATE, "Product", saved.getId(), getCurrentUserId(), "Created product: " + saved.getName());

        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());
        Product saved = productRepository.save(existing);

        auditLogService.log(AuditAction.UPDATE, "Product", saved.getId(), getCurrentUserId(), "Updated product: " + saved.getName());
        return ProductMapper.toResponse(saved);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
        auditLogService.log(AuditAction.DELETE, "Product", id, getCurrentUserId(), "Deleted product");
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    private User getCurrentUserId() {
        // temp until JWT
        User user = new User();
        user.setId(1L);
        return user;
    }
}
