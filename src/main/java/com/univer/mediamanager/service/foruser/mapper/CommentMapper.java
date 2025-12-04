package com.univer.mediamanager.service.foruser.mapper;

import com.univer.mediamanager.model.foruser.Comment;
import com.univer.mediamanager.model.foruser.dto.response.CommentResponseDto;
import com.univer.mediamanager.service.media.mapper.MediaItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;
    private final MediaItemMapper mediaItemMapper;

    public CommentResponseDto toResponseDto(Comment entity) {
        if (entity == null) {
            return null;
        }

        return CommentResponseDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .createdAt(entity.getCreatedAt())
                .user(userMapper.toResponseDto(entity.getUser()))
                .mediaItem(mediaItemMapper.toResponseDto(entity.getMediaItem()))
                .build();
    }
}