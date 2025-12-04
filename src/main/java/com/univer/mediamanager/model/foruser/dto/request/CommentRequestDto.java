package com.univer.mediamanager.model.foruser.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(max = 1000, message = "Комментарий не может превышать 1000 символов")
    @JsonProperty(required = true)
    private String text;

    @NotNull(message = "ID пользователя должен быть указан")
    @JsonProperty(required = true)
    private Long userId;

    @NotNull(message = "ID медиа должен быть указан")
    @JsonProperty(required = true)
    private Long mediaId;
}