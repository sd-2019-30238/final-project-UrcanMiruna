package com.books.addict.service.readService;

import com.books.addict.model.Book;
import com.books.addict.model.BookInfo;

import java.util.List;
import java.util.Optional;

public interface BookServiceR {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Integer id);
    List<Book> getBooksByAuthor(String username);
    List<BookInfo> getBooksInfos();
}
