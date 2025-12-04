package com.univer.mediamanager.service.foruser.mapper;

import com.univer.mediamanager.model.foruser.UserMediaStatus;
import com.univer.mediamanager.model.foruser.dto.response.UserMediaStatusResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMediaStatusMapper {

    public UserMediaStatusResponseDto toResponseDto(UserMediaStatus entity) {
        if (entity == null) {
            return null;
        }

        return UserMediaStatusResponseDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .mediaItem(entity.getMediaItem())
                .status(entity.getStatus())
                .rating(entity.getRating())
                .notes(entity.getNotes())
                .addedDate(entity.getAddedDate())
                .isFavorite(entity.isFavorite())
                .build();
    }
}