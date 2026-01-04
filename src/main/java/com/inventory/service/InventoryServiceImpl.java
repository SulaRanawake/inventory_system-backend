package com.inventory.service;

import com.inventory.entity.AuditAction;
import com.inventory.entity.Inventory;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final AuditLogService auditLogService;

    @Override
    public Inventory createInventory(Inventory inventory, String performedBy) {
        Inventory saved = inventoryRepository.save(inventory);
        auditLogService.log(AuditAction.CREATE, "Inventory", saved.getId(), performedBy,
                "Added inventory for product: " + saved.getProduct().getId() + ", quantity: " + saved.getQuantity());
        return saved;
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory, String performedBy) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id " + id));
        existing.setQuantity(inventory.getQuantity());
        existing.setLastUpdated(inventory.getLastUpdated());
        Inventory saved = inventoryRepository.save(existing);
        auditLogService.log(AuditAction.UPDATE, "Inventory", saved.getId(), performedBy,
                "Updated inventory for product: " + saved.getProduct().getId() + ", quantity: " + saved.getQuantity());
        return saved;
    }

    @Override
    public void deleteInventory(Long id, String performedBy) {
        inventoryRepository.deleteById(id);
        auditLogService.log(AuditAction.DELETE, "Inventory", id, performedBy, "Deleted inventory record");
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
}
