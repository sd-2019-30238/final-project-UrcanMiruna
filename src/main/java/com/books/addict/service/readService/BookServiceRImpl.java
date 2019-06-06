package com.books.addict.service.readService;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookServiceRImpl implements BookServiceR {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private FeedbackServiceR feedbackServiceR;
    @Autowired
    private  OrderServiceR orderServiceR;


    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }


    @Override
    public List<Book> getBooksByAuthor(String username) {
        List<Book> books= (List<Book>) bookRepository.findAll();
        books =  books.stream().filter(book -> book.getWriter().equalsIgnoreCase("username")).collect(Collectors.toList());
        return books;
    }

    @Override
    public List<BookInfo> getBooksInfos() {


       List<BookInfo> infos = new ArrayList<>();
        for(Book book:this.getAllBooks()){
            List<Feedback> list=feedbackServiceR.getAllFeedbacks().stream().filter(feedback -> feedback.getIdBook().equals(book.getId())).collect(Collectors.toList());
            double rates=0;
            for(Feedback feedback:list){
                rates+=feedback.getRate();
            }
            rates=rates/list.size();
            infos.add(new BookInfo(book.getId(), book.getName(), book.getType(), book.getDescriprion(),book.getPrice(), book.getWriter(), rates));
        }
        return infos;
    }
}
