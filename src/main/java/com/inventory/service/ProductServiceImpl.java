package com.inventory.service;

import com.inventory.enums.Enums.AuditAction;
import com.inventory.entity.Product;
import com.inventory.entity.User;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AuditLogService auditLogService;
    private final UserRepository userRepository;

    @Override
    public Product createProduct(Product product, String performedBy) {
//        temp until JWT
        User currentUser = userRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        product.setCreatedBy(currentUser);
//        temp
        Product saved = productRepository.save(product);
        auditLogService.log(AuditAction.CREATE, "Product", saved.getId(), performedBy, "Created product: " + saved.getName());
        return saved;
    }

    @Override
    public Product updateProduct(Long id, Product product, String performedBy) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setUpdatedAt(product.getUpdatedAt());
        Product saved = productRepository.save(existing);
        auditLogService.log(AuditAction.UPDATE, "Product", saved.getId(), performedBy, "Updated product: " + saved.getName());
        return saved;
    }

    @Override
    public void deleteProduct(Long id, String performedBy) {
        productRepository.deleteById(id);
        auditLogService.log(AuditAction.DELETE, "Product", id, performedBy, "Deleted product");
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
