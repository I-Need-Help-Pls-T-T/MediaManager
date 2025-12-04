package com.univer.mediamanager.repository.media.book;

import com.univer.mediamanager.model.enums.Genre;
import com.univer.mediamanager.model.media.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByReleaseYear(int releaseYear);
    List<Book> findByRatingGreaterThanEqual(double rating);
    List<Book> findByGenres(Genre genre);

    List<Book> findByPublisherContainingIgnoreCase(String publisher);
    List<Book> findBySeriesContainingIgnoreCase(String series);
    List<Book> findByAgeRating(int ageRating);
    List<Book> findByPageCountBetween(int minPageCount, int maxPageCount);
    List<Book> findByPageCountGreaterThanEqual(int pageCount);
    List<Book> findByBindingType(String bindingType);

    @Query("SELECT b FROM Book b WHERE b.circulating > 0")
    List<Book> findCurrentlyCirculatingBooks();

    @Query("SELECT b FROM Book b WHERE b.ageRating <= :maxAgeRating AND b.pageCount <= :maxPageCount")
    List<Book> findShortBooksForYoungReaders(@Param("maxAgeRating") int maxAgeRating,
                                             @Param("maxPageCount") int maxPageCount);

    @Query("SELECT AVG(b.pageCount) FROM Book b")
    Double findAveragePageCount();

    @Query("SELECT MAX(b.pageCount) FROM Book b")
    Integer findMaxPageCount();

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors WHERE b.id = :id")
    Optional<Book> findByIdWithAuthors(@Param("id") Long id);
}