package com.books.addict.service.readService;

import com.books.addict.model.Book;
import com.books.addict.model.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderServiceR {
    List<Reader> getAllReaders();
    List<Reader> getAllReaderForBook(Book book);
    Optional<Reader> getReaderbyId(Integer id);
    boolean checkValidity(String username, String password);
    Reader findByUsername(String username);
}
