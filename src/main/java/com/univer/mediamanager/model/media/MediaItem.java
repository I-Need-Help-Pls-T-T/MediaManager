package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.enums.Language;
import com.univer.mediamanager.model.enums.MediaType;
import com.univer.mediamanager.model.converter.GenreListConverter;
import com.univer.mediamanager.model.converter.LanguageListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "media_items")
@Inheritance(strategy = InheritanceType.JOINED)
public class MediaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    private double rating;

    @Convert(converter = LanguageListConverter.class)
    @Column(name = "languages", columnDefinition = "TEXT")
    private List<Language> language;

    @Convert(converter = GenreListConverter.class)
    @Column(name = "genres", columnDefinition = "TEXT")
    private List<Genre> genres;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;

    @Column(name = "box_office")
    private double boxOffice;

    @Column(name = "age_rating")
    private int ageRating;
}