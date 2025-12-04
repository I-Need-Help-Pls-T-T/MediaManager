package com.univer.mediamanager.repository.media;

import com.univer.mediamanager.model.media.MediaItem;
import com.univer.mediamanager.model.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {

    List<MediaItem> findByTitleContainingIgnoreCase(String title);

    List<MediaItem> findByReleaseYear(int releaseYear);

    List<MediaItem> findByRatingGreaterThanEqual(double rating);

    List<MediaItem> findByMediaType(MediaType mediaType);
}