package com.univer.mediamanager.service.media.mapper;

import com.univer.mediamanager.model.media.Book;
import com.univer.mediamanager.model.media.dto.request.BookRequestDto;
import com.univer.mediamanager.model.media.dto.response.BookResponseDto;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setCoverUrl(dto.getCoverURL());
        book.setDescription(dto.getDescription());
        book.setReleaseYear(dto.getReleaseYear());
        book.setRating(dto.getRating());
        book.setLanguage(dto.getLanguage());
        book.setGenres(dto.getGenres());
        book.setMediaType(dto.getMediaType());
        book.setBoxOffice(dto.getBoxOffice());

        book.setAuthors(dto.getAuthors());
        book.setPageCount(dto.getPageCount());
        book.setPublisher(dto.getPublisher());
        book.setSeries(dto.getSeries());
        book.setBindingType(dto.getBindingType());
        book.setAgeRating(dto.getAgeRating());
        book.setCirculating(dto.getCircleRating());

        return book;
    }

    public BookResponseDto toResponseDto(Book entity) {
        if (entity == null) {
            return null;
        }

        BookResponseDto dto = new BookResponseDto();

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

        dto.setAuthors(entity.getAuthors());
        dto.setPageCount(entity.getPageCount());
        dto.setPublisher(entity.getPublisher());
        dto.setSeries(entity.getSeries());
        dto.setBindingType(entity.getBindingType());
        dto.setAgeRating(entity.getAgeRating());
        dto.setCirculating(entity.getCirculating());

        return dto;
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public void updateEntityFromDto(BookRequestDto dto, Book entity) {
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

        setIfNotNull(entity::setAuthors, dto.getAuthors());
        entity.setPageCount(dto.getPageCount());
        setIfNotNull(entity::setPublisher, dto.getPublisher());
        setIfNotNull(entity::setSeries, dto.getSeries());
        setIfNotNull(entity::setBindingType, dto.getBindingType());
        entity.setAgeRating(dto.getAgeRating());
        entity.setCirculating(dto.getCircleRating());
    }
}