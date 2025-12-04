package com.univer.mediamanager.service.media.mapper;

import com.univer.mediamanager.model.media.MediaItem;
import com.univer.mediamanager.model.media.dto.request.MediaItemRequestDto;
import com.univer.mediamanager.model.media.dto.response.MediaItemResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MediaItemMapper {

    public MediaItem toEntity(MediaItemRequestDto dto) {
        if (dto == null) {
            return null;
        }

        MediaItem entity = new MediaItem();
        entity.setTitle(dto.getTitle());
        entity.setCoverUrl(dto.getCoverURL());
        entity.setDescription(dto.getDescription());
        entity.setReleaseYear(dto.getReleaseYear());
        entity.setRating(dto.getRating());
        entity.setLanguage(dto.getLanguage());
        entity.setGenres(dto.getGenres());
        entity.setMediaType(dto.getMediaType());
        entity.setBoxOffice(dto.getBoxOffice());

        return entity;
    }

    public MediaItemResponseDto toResponseDto(MediaItem entity) {
        if (entity == null) {
            return null;
        }

        MediaItemResponseDto dto = new MediaItemResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCoverURL(entity.getCoverUrl());
        dto.setDescription(entity.getDescription());
        dto.setReleaseYear(entity.getReleaseYear());
        dto.setRating(entity.getRating());
        dto.setLanguage(entity.getLanguage());
        dto.setGenres(entity.getGenres());
        dto.setMediaType(entity.getMediaType());
        dto.setBoxOffice(entity.getBoxOffice());

        return dto;
    }

    public void updateEntityFromDto(MediaItemRequestDto dto, MediaItem entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }
        if (dto.getCoverURL() != null) {
            entity.setCoverUrl(dto.getCoverURL());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        entity.setReleaseYear(dto.getReleaseYear());
        entity.setRating(dto.getRating());
        if (dto.getLanguage() != null) {
            entity.setLanguage(dto.getLanguage());
        }
        if (dto.getGenres() != null) {
            entity.setGenres(dto.getGenres());
        }
        if (dto.getMediaType() != null) {
            entity.setMediaType(dto.getMediaType());
        }
        entity.setBoxOffice(dto.getBoxOffice());
    }
}