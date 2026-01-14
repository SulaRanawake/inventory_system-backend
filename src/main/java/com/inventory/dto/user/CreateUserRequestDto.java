package com.inventory.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {
    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @NotBlank
    private String password;
}
