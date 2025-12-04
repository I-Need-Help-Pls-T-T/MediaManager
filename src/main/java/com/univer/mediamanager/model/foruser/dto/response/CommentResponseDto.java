package com.univer.mediamanager.model.foruser.dto.response;

import com.univer.mediamanager.model.media.dto.response.MediaItemResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class CommentResponseDto {
    private Long id;

    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(max = 1000, message = "Текст комментария нне может превышать 1000 символов")
    private String text;

    private LocalDateTime createdAt;

    private UserResponseDto user;
    private MediaItemResponseDto mediaItem;
}