package com.books.addict.service.readService;

import com.books.addict.model.Author;
import com.books.addict.model.Book;
import com.books.addict.model.Feedback;

import java.util.List;

public interface AuthorServiceR {
    List<Author> getAllAuthors();
    Author findByUsername(String username);
    List<Feedback> feedbacks(String username);
    boolean checkValidity(String username, String password);

}
