package com.univer.mediamanager.model.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreditRequestDto {
    private Long mediaItemId;

    private Long personId;

    @NotBlank(message = "Роль не может отсутствовать")
    private String role;
}
