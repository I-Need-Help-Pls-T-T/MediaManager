package com.univer.mediamanager.repository.media.tvshow;

import com.univer.mediamanager.model.media.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long>, TVShowCustomRepository {
    List<TVShow> findByTitleContainingIgnoreCase(String title);
    List<TVShow> findByRatingGreaterThanEqual(double rating);

    List<TVShow> findByCountryContainingIgnoreCase(String country);
    List<TVShow> findByAgeRating(int ageRating);
    List<TVShow> findByNumberOfEpisodesGreaterThanEqual(int numberOfEpisodes);

    @org.springframework.data.jpa.repository.Query("SELECT t FROM TVShow t LEFT JOIN FETCH t.directors WHERE t.id = :id")
    Optional<TVShow> findByIdWithCrew(Long id);
}