package com.univer.mediamanager.model.foruser;

import com.univer.mediamanager.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMediaStatus {
    private Long userId;

    private User user;

    private Long mediaItemId;

    private Status status;

    private int rating;

    private String notes;

    private int addedDate;

    private boolean isFavorite;
}
