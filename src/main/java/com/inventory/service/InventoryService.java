package com.inventory.service;

import com.inventory.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Inventory createInventory(Inventory inventory, String performedBy);

    Inventory updateInventory(Long id, Inventory inventory, String performedBy);

    void deleteInventory(Long id, String performedBy);

    Optional<Inventory> getInventoryById(Long id);

    List<Inventory> getAllInventory();
}
