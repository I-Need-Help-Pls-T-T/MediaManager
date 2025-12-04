package com.univer.mediamanager.model.foruser.dto.response;

import com.univer.mediamanager.model.enums.Status;
import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.UserMediaStatusId;
import com.univer.mediamanager.model.media.MediaItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Data
@Builder
@Jacksonized
public class UserMediaStatusResponseDto {
    private UserMediaStatusId id;

    private User user;

    private MediaItem mediaItem;

    private Status status;

    private double rating;

    private String notes;

    private LocalDate addedDate;

    private boolean isFavorite;
}
