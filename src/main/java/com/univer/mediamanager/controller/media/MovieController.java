package com.univer.mediamanager.controller.media;

import com.univer.mediamanager.model.media.dto.request.MovieRequestDto;
import com.univer.mediamanager.model.media.dto.response.MovieResponseDto;
import com.univer.mediamanager.service.media.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        List<MovieResponseDto> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-crew")
    public ResponseEntity<MovieResponseDto> getMovieByIdWithCrew(@PathVariable Long id) {
        return movieService.findByIdWithCrew(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody MovieRequestDto movieRequestDto) {
        MovieResponseDto newMovie = movieService.create(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Long id,
                                                        @Valid @RequestBody MovieRequestDto movieRequestDto) {
        return movieService.update(id, movieRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if (movieService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<MovieResponseDto>> searchByTitle(@RequestParam String title) {
        List<MovieResponseDto> movies = movieService.findByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/min-rating")
    public ResponseEntity<List<MovieResponseDto>> searchByMinRating(@RequestParam double rating) {
        List<MovieResponseDto> movies = movieService.findByRatingGreaterThan(rating);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/country")
    public ResponseEntity<List<MovieResponseDto>> searchByCountry(@RequestParam String country) {
        List<MovieResponseDto> movies = movieService.findByCountry(country);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/budget-range")
    public ResponseEntity<List<MovieResponseDto>> searchByBudgetBetween(
            @RequestParam int min,
            @RequestParam int max) {
        List<MovieResponseDto> movies = movieService.findByBudgetBetween(min, max);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/runtime-range")
    public ResponseEntity<List<MovieResponseDto>> searchByRuntimeBetween(
            @RequestParam int min,
            @RequestParam int max) {
        List<MovieResponseDto> movies = movieService.findByRuntimeBetween(min, max);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/crew-member")
    public ResponseEntity<List<MovieResponseDto>> searchByCrewMemberName(@RequestParam String name) {
        List<MovieResponseDto> movies = movieService.findByCrewMemberName(name);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/statistics/average-runtime")
    public ResponseEntity<Double> getAverageRuntime() {
        Double avgRuntime = movieService.getAverageRuntime();
        return ResponseEntity.ok(avgRuntime);
    }

    @GetMapping("/statistics/high-budget")
    public ResponseEntity<List<MovieResponseDto>> findHighBudgetMovies(@RequestParam double minBudget) {
        List<MovieResponseDto> movies = movieService.findHighBudgetMovies(minBudget);
        return ResponseEntity.ok(movies);
    }
}