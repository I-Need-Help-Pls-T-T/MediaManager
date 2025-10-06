package com.univer.mediamanager.model.foruser.dto.request;

import com.univer.mediamanager.model.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMediaStatusRequestDto {
    @NotBlank(message = "Пользователь не может отсутствовать")
    private Long userId;

    @NotBlank(message = "Медиа не может отсутствовать")
    private Long mediaItemId;

    private Status status;

    @Min(value = 0, message = "Минимальный рейтинг 0")
    private double rating;

    private String notes;

    @NotNull(message = "Дата добавления обязательна")
    @PastOrPresent(message = "Дата не может быть из будущего")
    private LocalDate addedDate;

    private boolean isFavorite;
}