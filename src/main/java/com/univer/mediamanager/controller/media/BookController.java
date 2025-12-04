package com.univer.mediamanager.controller.media;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.media.dto.request.BookRequestDto;
import com.univer.mediamanager.model.media.dto.response.BookResponseDto;
import com.univer.mediamanager.service.media.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-authors")
    public ResponseEntity<BookResponseDto> getBookByIdWithAuthors(@PathVariable Long id) {
        return bookService.findByIdWithAuthors(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto newBook = bookService.create(bookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id,
                                                      @Valid @RequestBody BookRequestDto bookRequestDto) {
        return bookService.update(id, bookRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookResponseDto>> searchByTitle(@RequestParam String title) {
        List<BookResponseDto> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/min-rating")
    public ResponseEntity<List<BookResponseDto>> searchByMinRating(@RequestParam double rating) {
        List<BookResponseDto> books = bookService.findByRatingGreaterThan(rating);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/publisher")
    public ResponseEntity<List<BookResponseDto>> searchByPublisher(@RequestParam String publisher) {
        List<BookResponseDto> books = bookService.findByPublisher(publisher);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/age-rating")
    public ResponseEntity<List<BookResponseDto>> searchByAgeRating(@RequestParam int ageRating) {
        List<BookResponseDto> books = bookService.findByAgeRating(ageRating);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/page-count-range")
    public ResponseEntity<List<BookResponseDto>> searchByPageCountBetween(
            @RequestParam int min,
            @RequestParam int max) {
        List<BookResponseDto> books = bookService.findByPageCountBetween(min, max);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<BookResponseDto>> searchByAuthorName(@RequestParam String name) {
        List<BookResponseDto> books = bookService.findByAuthorName(name);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/statistics/average-pages")
    public ResponseEntity<Double> getAveragePageCount() {
        Double avgPages = bookService.getAveragePageCount();
        return ResponseEntity.ok(avgPages);
    }
}