package com.univer.mediamanager.service.media;

import com.univer.mediamanager.model.media.Movie;
import com.univer.mediamanager.model.media.dto.request.MovieRequestDto;
import com.univer.mediamanager.model.media.dto.response.MovieResponseDto;
import com.univer.mediamanager.repository.media.movie.MovieRepository;
import com.univer.mediamanager.service.media.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findAll() {
        log.info("Getting all movies");
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MovieResponseDto> findById(Long id) {
        log.info("Getting movie by id: {}", id);
        return movieRepository.findById(id)
                .map(movieMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Optional<MovieResponseDto> findByIdWithCrew(Long id) {
        log.info("Getting movie with crew by id: {}", id);
        return movieRepository.findByIdWithCrew(id)
                .map(movieMapper::toResponseDto);
    }

    public MovieResponseDto create(MovieRequestDto requestDto) {
        log.info("Creating new movie: {}", requestDto.getTitle());
        Movie movie = movieMapper.toEntity(requestDto);
        Movie savedMovie = movieRepository.save(movie);
        log.info("Movie created with id: {}", savedMovie.getId());
        return movieMapper.toResponseDto(savedMovie);
    }

    public Optional<MovieResponseDto> update(Long id, MovieRequestDto requestDto) {
        log.info("Updating movie with id: {}", id);
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    movieMapper.updateEntityFromDto(requestDto, existingMovie);
                    Movie updatedMovie = movieRepository.save(existingMovie);
                    log.info("Movie updated with id: {}", updatedMovie.getId());
                    return movieMapper.toResponseDto(updatedMovie);
                });
    }

    public boolean delete(Long id) {
        log.info("Deleting movie with id: {}", id);
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            log.info("Movie deleted with id: {}", id);
            return true;
        }
        log.warn("Movie not found for deletion with id: {}", id);
        return false;
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByTitle(String title) {
        log.info("Searching movies by title: {}", title);
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByCountry(String country) {
        log.info("Searching movies by country: {}", country);
        return movieRepository.findByCountryContainingIgnoreCase(country)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntimeBetween(int minRuntime, int maxRuntime) {
        log.info("Searching movies by runtime between {} and {}", minRuntime, maxRuntime);
        return movieRepository.findByRuntimeBetween(minRuntime, maxRuntime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByAgeRating(int ageRating) {
        log.info("Searching movies by age rating: {}", ageRating);
        return movieRepository.findByAgeRating(ageRating)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByBudgetGreaterThan(int budget) {
        log.info("Searching movies with budget greater than: {}", budget);
        return movieRepository.findByBudgetGreaterThanEqual(budget)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByBudgetBetween(int minBudget, int maxBudget) {
        log.info("Searching movies by budget between {} and {}", minBudget, maxBudget);
        return movieRepository.findByBudgetBetween(minBudget, maxBudget)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByCountryAndMinRating(String country, double minRating) {
        log.info("Searching movies by country {} and min rating {}", country, minRating);
        return movieRepository.findByCountryAndMinRating(country, minRating)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findFamilyFriendlyMovies(int maxRuntime, int maxAgeRating) {
        log.info("Searching family friendly movies with max runtime {} and max age rating {}", maxRuntime, maxAgeRating);
        return movieRepository.findFamilyFriendlyMovies(maxRuntime, maxAgeRating)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Методы из кастомного репозитория

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findMoviesWithCrew() {
        log.info("Searching movies with crew information");
        return movieRepository.findMoviesWithCrew()
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByCrewMemberName(String name) {
        log.info("Searching movies by crew member name: {}", name);
        return movieRepository.findByCrewMemberName(name)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findHighBudgetMovies(double minBudget) {
        log.info("Searching high budget movies with min budget: {}", minBudget);
        return movieRepository.findHighBudgetMovies(minBudget)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Статистические методы

    @Transactional(readOnly = true)
    public Double getAverageRuntime() {
        log.info("Calculating average movie runtime");
        return movieRepository.findAverageRuntime();
    }

    @Transactional(readOnly = true)
    public Integer getMaxBudget() {
        log.info("Getting maximum movie budget");
        return movieRepository.findMaxBudget();
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findTopByBoxOffice(int limit) {
        log.info("Getting top {} movies by box office", limit);
        return movieRepository.findTopByBoxOffice(limit)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByReleaseYear(int releaseYear) {
        log.info("Searching movies by release year: {}", releaseYear);
        return movieRepository.findByReleaseYear(releaseYear)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRatingGreaterThan(double rating) {
        log.info("Searching movies with rating greater than: {}", rating);
        return movieRepository.findByRatingGreaterThanEqual(rating)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntimeGreaterThanEqual(int runtime) {
        log.info("Searching movies with runtime greater than or equal to: {} minutes", runtime);
        return movieRepository.findByRuntimeGreaterThanEqual(runtime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntime(int runtime) {
        log.info("Searching movies with runtime: {} minutes", runtime);
        return movieRepository.findByRuntime(runtime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntimeGreaterThan(int runtime) {
        log.info("Searching movies with runtime greater than: {} minutes", runtime);
        return movieRepository.findByRuntimeGreaterThan(runtime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntimeLessThanEqual(int runtime) {
        log.info("Searching movies with runtime less than or equal to: {} minutes", runtime);
        return movieRepository.findByRuntimeLessThanEqual(runtime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovieResponseDto> findByRuntimeLessThan(int runtime) {
        log.info("Searching movies with runtime less than: {} minutes", runtime);
        return movieRepository.findByRuntimeLessThan(runtime)
                .stream()
                .map(movieMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}