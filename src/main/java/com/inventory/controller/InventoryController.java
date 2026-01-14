package com.inventory.controller;

import com.inventory.dto.inventory.InventoryResponseDTO;
import com.inventory.dto.inventory.UpdateInventoryRequestDTO;
import com.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PutMapping("/{productId}")
    public InventoryResponseDTO updateQuantity(
            @PathVariable Long productId, @Valid @RequestBody UpdateInventoryRequestDTO dto) {
        return inventoryService.updateInventory(productId, dto);
    }

    @GetMapping("/{productId}")
    public InventoryResponseDTO getByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @GetMapping
    public List<InventoryResponseDTO> getAll() {
        return inventoryService.getAllInventory();
    }
}
