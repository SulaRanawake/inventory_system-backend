package com.inventory.service;

import com.inventory.dto.inventory.InventoryResponseDTO;
import com.inventory.dto.inventory.UpdateInventoryRequestDTO;
import com.inventory.entity.User;
import com.inventory.enums.Enums.AuditAction;
import com.inventory.entity.Inventory;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.InventoryMapper;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final AuditLogService auditLogService;

    @Override
    public InventoryResponseDTO updateInventory(Long productId, UpdateInventoryRequestDTO dto) {
        Inventory existing = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Product id " + productId));
        existing.setQuantity(dto.getQuantity());
        existing.setLastUpdated(LocalDateTime.now());
        Inventory saved = inventoryRepository.save(existing);
        auditLogService.log(AuditAction.UPDATE, "Inventory", saved.getId(), getCurrentUserId(),
                "Updated inventory for product: " + saved.getProduct().getId() + ", quantity: " + saved.getQuantity());
        return InventoryMapper.toResponse(saved);
    }

    @Override
    public InventoryResponseDTO getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .map(InventoryMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with Product id " + productId));
    }

    @Override
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::toResponse)
                .toList();
    }

    private User getCurrentUserId() {
        // temp until JWT
        User user = new User();
        user.setId(1L);
        return user;
    }
}
