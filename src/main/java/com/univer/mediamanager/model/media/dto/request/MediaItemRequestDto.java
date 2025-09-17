package com.univer.mediamanager.model.media.dto.request;

import com.univer.mediamanager.model.MediaType;
import com.univer.mediamanager.model.common.Genre;
import com.univer.mediamanager.model.common.Language;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MediaItemRequestDto {
    @NotBlank(message = "Название не может быть пустым")
    private String title;

    private String coverURL;

    private String description;

    private int releaseYear;

    @Min(value = 0, message = "Минимальный рейтинг может быть 0")
    @Max(value = 10, message = "Максимальный рейтинг может быть 10")
    private int rating;

    private List<Language> language;

    private List<Genre> genres;

    @NotNull(message = "MediaType не может быть 0")
    private MediaType mediaType;
}
