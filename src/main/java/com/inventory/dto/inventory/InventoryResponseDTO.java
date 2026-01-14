package com.inventory.dto.inventory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryResponseDTO {
    private Long inventoryId;
    private Long productId;
    private String productName;
    private Integer quantity;
}
