package com.books.addict.service.readService;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AuthorServiceRImpls implements AuthorServiceR {

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
    public Author findByUsername(String username) {
        return authorRepository.findByUsername(username);
    }

    @Override
    public List<Feedback> feedbacks(String username) {
        return (List<Feedback>) feedBackRepository.findAll();
    }

    @Override
    public boolean checkValidity(String username, String password) {
        if(authorRepository.findByUsername(username)!=null && authorRepository.findByUsername(username).getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
