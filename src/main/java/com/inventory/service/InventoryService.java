package com.inventory.service;

import com.inventory.dto.inventory.InventoryResponseDTO;
import com.inventory.dto.inventory.UpdateInventoryRequestDTO;

import java.util.List;

public interface InventoryService {

    InventoryResponseDTO updateInventory(Long productId, UpdateInventoryRequestDTO dto);

    InventoryResponseDTO getInventoryByProductId(Long productId);

    List<InventoryResponseDTO> getAllInventory();
}
