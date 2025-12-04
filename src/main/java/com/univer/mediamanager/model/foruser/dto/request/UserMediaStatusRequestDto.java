package com.univer.mediamanager.model.foruser.dto.request;

import com.univer.mediamanager.model.enums.Status;
import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.media.MediaItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserMediaStatusRequestDto {
    private Long userId;

    private Long mediaItemId;

    private Status status;

    private double rating;

    private String notes;

    private boolean isFavorite;
}
