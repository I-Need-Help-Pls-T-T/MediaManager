package com.univer.mediamanager.repository.media.book;

import com.univer.mediamanager.model.media.Book;
import java.util.List;

public interface BookCustomRepository {

    List<Book> findBooksWithAuthors();

    List<Book> findByAuthorName(String name);

    List<Book> findLongestBooks(int minPageCount);
}