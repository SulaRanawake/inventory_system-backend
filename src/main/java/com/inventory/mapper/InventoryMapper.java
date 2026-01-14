package com.inventory.mapper;

import com.inventory.dto.inventory.InventoryResponseDTO;
import com.inventory.entity.Inventory;

public class InventoryMapper {
    public static InventoryResponseDTO toResponse(Inventory inventory) {
        return InventoryResponseDTO.builder()
                .inventoryId(inventory.getId())
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .quantity(inventory.getQuantity())
                .build();
    }
}
