package com.inventory.dto.inventory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInventoryRequestDTO {

    @NotNull
    @PositiveOrZero
    private Integer quantity;
}
