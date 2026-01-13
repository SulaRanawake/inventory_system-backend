package com.inventory.dto.product;

import com.inventory.enums.Enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private ProductStatus status;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
