package com.univer.mediamanager.controller.media;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.enums.Language;
import com.univer.mediamanager.model.enums.MediaType;
import com.univer.mediamanager.model.media.dto.request.MediaItemRequestDto;
import com.univer.mediamanager.model.media.dto.response.MediaItemResponseDto;
import com.univer.mediamanager.service.media.MediaItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaItemController {

    private final MediaItemService mediaItemService;

    // GET все медиа-элементы
    @GetMapping
    public ResponseEntity<List<MediaItemResponseDto>> getAllMediaItems() {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findAll();
        return ResponseEntity.ok(mediaItems);
    }

    // GET по ID
    @GetMapping("/{id}")
    public ResponseEntity<MediaItemResponseDto> getMediaItemById(@PathVariable Long id) {
        return mediaItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST создать новый медиа-элемент
    @PostMapping
    public ResponseEntity<MediaItemResponseDto> createMediaItem(
            @Valid @RequestBody MediaItemRequestDto requestDto) {
        MediaItemResponseDto createdItem = mediaItemService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // PUT обновить медиа-элемент
    @PutMapping("/{id}")
    public ResponseEntity<MediaItemResponseDto> updateMediaItem(
            @PathVariable Long id,
            @Valid @RequestBody MediaItemRequestDto requestDto) {
        return mediaItemService.update(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE удалить медиа-элемент
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaItem(@PathVariable Long id) {
        if (mediaItemService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET поиск по названию
    @GetMapping("/search/title")
    public ResponseEntity<List<MediaItemResponseDto>> searchByTitle(
            @RequestParam String title) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByTitleContaining(title);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по году выпуска
    @GetMapping("/search/year")
    public ResponseEntity<List<MediaItemResponseDto>> searchByReleaseYear(
            @RequestParam int releaseYear) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByReleaseYear(releaseYear);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по рейтингу (больше или равно)
    @GetMapping("/search/rating")
    public ResponseEntity<List<MediaItemResponseDto>> searchByRatingGreaterThan(
            @RequestParam double rating) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByRatingGreaterThan(rating);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по диапазону рейтинга
    @GetMapping("/search/rating-range")
    public ResponseEntity<List<MediaItemResponseDto>> searchByRatingBetween(
            @RequestParam double minRating,
            @RequestParam double maxRating) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByRatingBetween(minRating, maxRating);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по типу медиа
    @GetMapping("/search/type")
    public ResponseEntity<List<MediaItemResponseDto>> searchByMediaType(
            @RequestParam MediaType mediaType) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByMediaType(mediaType);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по жанру
    @GetMapping("/search/genre")
    public ResponseEntity<List<MediaItemResponseDto>> searchByGenre(
            @RequestParam Genre genre) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByGenre(genre);
        return ResponseEntity.ok(mediaItems);
    }

    // GET поиск по языку
    @GetMapping("/search/language")
    public ResponseEntity<List<MediaItemResponseDto>> searchByLanguage(
            @RequestParam Language language) {
        List<MediaItemResponseDto> mediaItems = mediaItemService.findByLanguage(language);
        return ResponseEntity.ok(mediaItems);
    }
}