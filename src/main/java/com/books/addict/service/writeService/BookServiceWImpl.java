package com.books.addict.service.writeService;

import com.books.addict.model.Book;
import com.books.addict.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookServiceWImpl implements BookServiceW {

    @Autowired
    private BookRepository bookRepository;




    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }


}
