package com.univer.mediamanager.model.media.dto.response;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.enums.Language;
import com.univer.mediamanager.model.enums.MediaType;
import lombok.Data;
import java.util.List;

@Data
public class MediaItemResponseDto {
    private Long id;

    private String title;

    private String coverURL;

    private String description;

    private int releaseYear;

    private double rating;

    private List<Language> language;

    private List<Genre> genres;

    private MediaType mediaType;

    private double boxOffice;
}
