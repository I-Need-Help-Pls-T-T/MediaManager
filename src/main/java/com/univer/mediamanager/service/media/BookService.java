package com.univer.mediamanager.service.media;

import com.univer.mediamanager.model.media.Book;
import com.univer.mediamanager.model.media.dto.request.BookRequestDto;
import com.univer.mediamanager.model.media.dto.response.BookResponseDto;
import com.univer.mediamanager.repository.media.book.BookRepository;
import com.univer.mediamanager.service.media.mapper.BookMapper;
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
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public List<BookResponseDto> findAll() {
        log.info("Getting all books");
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<BookResponseDto> findById(Long id) {
        log.info("Getting book by id: {}", id);
        return bookRepository.findById(id)
                .map(bookMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Optional<BookResponseDto> findByIdWithAuthors(Long id) {
        log.info("Getting book with authors by id: {}", id);
        return bookRepository.findByIdWithAuthors(id)
                .map(bookMapper::toResponseDto);
    }

    public BookResponseDto create(BookRequestDto requestDto) {
        log.info("Creating new book: {}", requestDto.getTitle());
        Book book = bookMapper.toEntity(requestDto);
        Book savedBook = bookRepository.save(book);
        log.info("Book created with id: {}", savedBook.getId());
        return bookMapper.toResponseDto(savedBook);
    }

    public Optional<BookResponseDto> update(Long id, BookRequestDto requestDto) {
        log.info("Updating book with id: {}", id);
        return bookRepository.findById(id)
                .map(existingBook -> {
                    bookMapper.updateEntityFromDto(requestDto, existingBook);
                    Book updatedBook = bookRepository.save(existingBook);
                    log.info("Book updated with id: {}", updatedBook.getId());
                    return bookMapper.toResponseDto(updatedBook);
                });
    }

    public boolean delete(Long id) {
        log.info("Deleting book with id: {}", id);
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            log.info("Book deleted with id: {}", id);
            return true;
        }
        log.warn("Book not found for deletion with id: {}", id);
        return false;
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByTitle(String title) {
        log.info("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByReleaseYear(int releaseYear) {
        log.info("Searching books by release year: {}", releaseYear);
        return bookRepository.findByReleaseYear(releaseYear)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByRatingGreaterThan(double rating) {
        log.info("Searching books with rating greater than or equal to: {}", rating);
        return bookRepository.findByRatingGreaterThanEqual(rating)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByPublisher(String publisher) {
        log.info("Searching books by publisher: {}", publisher);
        return bookRepository.findByPublisherContainingIgnoreCase(publisher)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByAgeRating(int ageRating) {
        log.info("Searching books by age rating: {}", ageRating);
        return bookRepository.findByAgeRating(ageRating)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByPageCountBetween(int minPageCount, int maxPageCount) {
        log.info("Searching books by page count between {} and {}", minPageCount, maxPageCount);
        return bookRepository.findByPageCountBetween(minPageCount, maxPageCount)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findByAuthorName(String name) {
        log.info("Searching books by author name: {}", name);
        return bookRepository.findByAuthorName(name)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findLongestBooks(int minPageCount) {
        log.info("Searching books with page count greater than or equal to: {}", minPageCount);
        return bookRepository.findLongestBooks(minPageCount)
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Double getAveragePageCount() {
        log.info("Calculating average book page count");
        return bookRepository.findAveragePageCount();
    }
}