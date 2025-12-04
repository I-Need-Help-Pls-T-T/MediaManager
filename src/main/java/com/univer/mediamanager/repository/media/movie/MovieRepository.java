package com.univer.mediamanager.repository.media.movie;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.media.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository {
    List<Movie> findByCountryContainingIgnoreCase(String country);
    List<Movie> findByRuntimeBetween(int minRuntime, int maxRuntime);
    List<Movie> findByRuntimeGreaterThanEqual(int runtime);
    List<Movie> findByAgeRating(int ageRating);
    List<Movie> findByBudgetGreaterThanEqual(int budget);
    List<Movie> findByBudgetBetween(int minBudget, int maxBudget);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByReleaseYear(int releaseYear);

    List<Movie> findByRatingGreaterThanEqual(double rating);

    List<Movie> findByRuntime(int runtime);

    List<Movie> findByRuntimeLessThanEqual(int runtime);

    List<Movie> findByRuntimeLessThan(int runtime);

    List<Movie> findByRuntimeGreaterThan(int runtime);

    List<Movie> findByGenres(Genre genre);

    @Query("SELECT m FROM Movie m WHERE m.country = :country AND m.rating >= :minRating")
    List<Movie> findByCountryAndMinRating(@Param("country") String country,
                                          @Param("minRating") double minRating);

    @Query("SELECT m FROM Movie m WHERE m.runtime <= :maxRuntime AND m.ageRating <= :maxAgeRating")
    List<Movie> findFamilyFriendlyMovies(@Param("maxRuntime") int maxRuntime,
                                         @Param("maxAgeRating") int maxAgeRating);

    @Query("SELECT AVG(m.runtime) FROM Movie m")
    Double findAverageRuntime();

    @Query("SELECT MAX(m.budget) FROM Movie m")
    Integer findMaxBudget();

    @Query("SELECT m FROM Movie m ORDER BY m.boxOffice DESC LIMIT :limit")
    List<Movie> findTopByBoxOffice(@Param("limit") int limit);

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.directors LEFT JOIN FETCH m.actors WHERE m.id = :id")
    Optional<Movie> findByIdWithCrew(@Param("id") Long id);
}