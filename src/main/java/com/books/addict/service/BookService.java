package com.books.addict.service;

import com.books.addict.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Integer id);
    void addBook(Book book);
    void deleteBook(Book book);
    void updateBook(Book book);
    List<Book> getBooksByAuthor(String username);
}
