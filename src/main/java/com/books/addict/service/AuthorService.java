package com.books.addict.service;

import com.books.addict.model.Author;
import com.books.addict.model.Book;
import com.books.addict.model.Feedback;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    void addAuthor(Author author);
    void deleteAuthor(Author author);
    void updateAuthor(Author author);
    Author findByUsername(String username);
    void addBook(Book book);
    List<Feedback> feedbacks(String username);

}
