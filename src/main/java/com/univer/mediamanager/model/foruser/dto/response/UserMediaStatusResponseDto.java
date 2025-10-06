package com.univer.mediamanager.model.foruser.dto.response;

import com.univer.mediamanager.model.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMediaStatusResponseDto {
    private Long userId;

    private Long mediaItemId;

    private Status status;

    private double rating;

    private String notes;

    private LocalDate addedDate;

    private boolean isFavorite;
}
