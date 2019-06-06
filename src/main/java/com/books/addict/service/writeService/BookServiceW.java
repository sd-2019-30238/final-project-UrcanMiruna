package com.books.addict.service.writeService;

import com.books.addict.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceW {
    void addBook(Book book);
    void deleteBook(Book book);
    void updateBook(Book book);
}
