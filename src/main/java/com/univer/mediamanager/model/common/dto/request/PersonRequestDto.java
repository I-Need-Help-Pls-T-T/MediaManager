package com.univer.mediamanager.model.common.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonRequestDto {

    @NotBlank(message = "Имя не может отсутствовать")
    private String name;

    @Min(value = 0, message = "Минимальный рейтинг 0")
    private double rating;
}
