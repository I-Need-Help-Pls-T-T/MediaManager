package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Genre;
import com.univer.mediamanager.model.common.Language;
import com.univer.mediamanager.model.MediaType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MediaItem {
    private Long id;

    private String title;

    private String coverUrl;

    private String description;

    private int releaseYear;

    private int rating;

    private List<Language> language;

    private List<Genre> genres;

    private MediaType mediaType;
}
