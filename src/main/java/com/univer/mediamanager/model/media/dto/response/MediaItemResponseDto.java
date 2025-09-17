package com.univer.mediamanager.model.media.dto.response;

import com.univer.mediamanager.model.MediaType;
import com.univer.mediamanager.model.common.Genre;
import com.univer.mediamanager.model.common.Language;
import lombok.Data;
import java.util.List;

@Data
public class MediaItemResponseDto {
    private Long id;

    private String title;

    private String coverURL;

    private String description;

    private int releaseYear;

    private int rating;

    private List<Language> language;

    private List<Genre> genres;

    private MediaType mediaType;
}
