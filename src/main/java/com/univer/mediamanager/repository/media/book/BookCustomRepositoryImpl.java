package com.univer.mediamanager.repository.media.book;

import com.univer.mediamanager.model.media.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class BookCustomRepositoryImpl implements BookCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksWithAuthors() {
        return entityManager.createQuery(
                        "SELECT DISTINCT b FROM Book b " +
                                "LEFT JOIN FETCH b.authors " +
                                "WHERE b.authors IS NOT EMPTY", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        return entityManager.createQuery(
                        "SELECT DISTINCT b FROM Book b " +
                                "LEFT JOIN b.authors a " +
                                "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))", Book.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Book> findLongestBooks(int minPageCount) {
        return entityManager.createQuery(
                        "SELECT b FROM Book b WHERE b.pageCount >= :minPageCount ORDER BY b.pageCount DESC", Book.class)
                .setParameter("minPageCount", minPageCount)
                .getResultList();
    }
}