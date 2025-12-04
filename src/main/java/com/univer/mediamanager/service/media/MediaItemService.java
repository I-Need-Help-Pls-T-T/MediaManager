package com.univer.mediamanager.service.media;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.enums.Language;
import com.univer.mediamanager.model.enums.MediaType;
import com.univer.mediamanager.model.media.MediaItem;
import com.univer.mediamanager.model.media.dto.request.MediaItemRequestDto;
import com.univer.mediamanager.model.media.dto.response.MediaItemResponseDto;
import com.univer.mediamanager.service.media.mapper.MediaItemMapper;
import com.univer.mediamanager.repository.media.MediaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MediaItemService {

    private final MediaItemRepository mediaItemRepository;
    private final MediaItemMapper mediaItemMapper;

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findAll() {
        return mediaItemRepository.findAll()
                .stream()
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<MediaItemResponseDto> findById(Long id) {
        return mediaItemRepository.findById(id)
                .map(mediaItemMapper::toResponseDto);
    }

    public MediaItemResponseDto create(MediaItemRequestDto requestDto) {
        MediaItem mediaItem = mediaItemMapper.toEntity(requestDto);
        MediaItem savedMediaItem = mediaItemRepository.save(mediaItem);
        return mediaItemMapper.toResponseDto(savedMediaItem);
    }

    public Optional<MediaItemResponseDto> update(Long id, MediaItemRequestDto requestDto) {
        return mediaItemRepository.findById(id)
                .map(existingMediaItem -> {
                    mediaItemMapper.updateEntityFromDto(requestDto, existingMediaItem);
                    MediaItem updatedMediaItem = mediaItemRepository.save(existingMediaItem);
                    return mediaItemMapper.toResponseDto(updatedMediaItem);
                });
    }

    public boolean delete(Long id) {
        if (mediaItemRepository.existsById(id)) {
            mediaItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByTitleContaining(String title) {
        return mediaItemRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByReleaseYear(int releaseYear) {
        return mediaItemRepository.findByReleaseYear(releaseYear)
                .stream()
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByRatingGreaterThan(double rating) {
        return mediaItemRepository.findByRatingGreaterThanEqual(rating)
                .stream()
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByMediaType(MediaType mediaType) {
        return mediaItemRepository.findByMediaType(mediaType)
                .stream()
                .map(mediaItemMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByGenre(Genre genre) {
        return mediaItemRepository.findAll()
                .stream()
                .filter(item -> item.getGenres() != null && item.getGenres().contains(genre))
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByLanguage(Language language) {
        return mediaItemRepository.findAll()
                .stream()
                .filter(item -> item.getLanguage() != null && item.getLanguage().contains(language))
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MediaItemResponseDto> findByRatingBetween(double minRating, double maxRating) {
        return mediaItemRepository.findAll()
                .stream()
                .filter(item -> item.getRating() >= minRating && item.getRating() <= maxRating)
                .map(mediaItemMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<MediaItem> getById(Long id) {
        return mediaItemRepository.findById(id);
    }

//    @Transactional(readOnly = true)
//    public int findByAgeRatingGreaterThanEqual(int ageRating) {
////        return mediaItemRepository.findByRatingGreaterThanEqual(rating)
////                .stream()
////                .map(mediaItemMapper::toResponseDto)
////                .toList();
//    }
}