package com.univer.mediamanager.service.media.mapper;

import com.univer.mediamanager.model.media.Movie;
import com.univer.mediamanager.model.media.dto.request.MovieRequestDto;
import com.univer.mediamanager.model.media.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Movie movie = new Movie();

        movie.setTitle(dto.getTitle());
        movie.setCoverUrl(dto.getCoverURL());
        movie.setDescription(dto.getDescription());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setRating(dto.getRating());
        movie.setLanguage(dto.getLanguage());
        movie.setGenres(dto.getGenres());
        movie.setMediaType(dto.getMediaType());
        movie.setBoxOffice(dto.getBoxOffice());

        movie.setCountry(dto.getCountry());
        movie.setRuntime(dto.getRuntime());
        movie.setBudget(dto.getBudget());
        movie.setAgeRating(dto.getAgeRating());
        movie.setDirectors(dto.getDirectors());
        movie.setScreenwriters(dto.getScreenwriters());
        movie.setProducers(dto.getProducers());
        movie.setActors(dto.getActors());
        movie.setCinematographers(dto.getCinematographers());
        movie.setComposers(dto.getComposers());
        movie.setEditors(dto.getEditors());

        return movie;
    }

    public MovieResponseDto toResponseDto(Movie entity) {
        if (entity == null) {
            return null;
        }

        MovieResponseDto dto = new MovieResponseDto();

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

        dto.setCountry(entity.getCountry());
        dto.setRuntime(entity.getRuntime());
        dto.setBudget(entity.getBudget());
        dto.setAgeRating(entity.getAgeRating());
        dto.setDirectors(entity.getDirectors());
        dto.setScreenwriters(entity.getScreenwriters());
        dto.setProducers(entity.getProducers());
        dto.setActors(entity.getActors());
        dto.setCinematographers(entity.getCinematographers());
        dto.setComposers(entity.getComposers());
        dto.setEditors(entity.getEditors());

        return dto;
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public void updateEntityFromDto(MovieRequestDto dto, Movie entity) {
        if (dto == null || entity == null) return;

        setIfNotNull(entity::setTitle, dto.getTitle());
        setIfNotNull(entity::setCoverUrl, dto.getCoverURL());
        setIfNotNull(entity::setDescription, dto.getDescription());
        entity.setReleaseYear(dto.getReleaseYear());
        entity.setRating(dto.getRating());
        setIfNotNull(entity::setLanguage, dto.getLanguage());
        setIfNotNull(entity::setGenres, dto.getGenres());
        setIfNotNull(entity::setMediaType, dto.getMediaType());
        entity.setBoxOffice(dto.getBoxOffice());
        setIfNotNull(entity::setCountry, dto.getCountry());
        entity.setRuntime(dto.getRuntime());
        entity.setBudget(dto.getBudget());
        entity.setAgeRating(dto.getAgeRating());
        setIfNotNull(entity::setDirectors, dto.getDirectors());
        setIfNotNull(entity::setScreenwriters, dto.getScreenwriters());
        setIfNotNull(entity::setProducers, dto.getProducers());
        setIfNotNull(entity::setActors, dto.getActors());
        setIfNotNull(entity::setCinematographers, dto.getCinematographers());
        setIfNotNull(entity::setComposers, dto.getComposers());
        setIfNotNull(entity::setEditors, dto.getEditors());
    }
}