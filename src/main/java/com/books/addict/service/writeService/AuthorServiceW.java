package com.books.addict.service.writeService;

import com.books.addict.model.Author;
import com.books.addict.model.Book;
import com.books.addict.model.Feedback;

import java.util.List;

public interface AuthorServiceW {
    void addAuthor(Author author);
    void deleteAuthor(Author author);
    void updateAuthor(Author author);
    void addBook(Book book);
    void deleteBook(Book book);
}
