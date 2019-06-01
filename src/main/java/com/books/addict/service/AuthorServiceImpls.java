package com.books.addict.service;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;


@Service
@Transactional
public class AuthorServiceImpls implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors= (List<Author>) authorRepository.findAll();
        return authors;
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author findByUsername(String username) {
        return authorRepository.findByUsername(username);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Feedback> feedbacks(String username) {
        return (List<Feedback>) feedBackRepository.findAll();
    }
}
