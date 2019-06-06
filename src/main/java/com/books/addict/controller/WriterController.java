package com.books.addict.controller;

import com.books.addict.model.*;
import com.books.addict.service.readService.AuthorServiceR;
import com.books.addict.service.readService.BookServiceR;
import com.books.addict.service.readService.FeedbackServiceR;
import com.books.addict.service.readService.OrderServiceR;
import com.books.addict.service.writeService.AuthorServiceW;
import com.books.addict.service.writeService.BookServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/writerPage")
public class WriterController {

    @Autowired
    private AuthorServiceR authorServiceR;
    @Autowired
    private AuthorServiceW authorServiceW;
    private Credentials credentials;
    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private FeedbackServiceR feedbackServiceR;
    @Autowired
    private BookServiceW bookServiceW;
    @Autowired
    private BookServiceR bookServiceR;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @GetMapping("/loginAuth")
    public String login(){
        return "/loginAuth";
    }


    @PostMapping("/loginAuth")
    public String login(@Valid @ModelAttribute("logg") Credentials credentials, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("eroare");
            return "/logorreg";
        }
        System.out.println(credentials.getUsername()+"  "+credentials.getPassword());
        if(authorServiceR.findByUsername(credentials.getUsername())!=null && authorServiceR.findByUsername(credentials.getUsername()).getPassword().equals(credentials.getPassword())){
            this.credentials=credentials;
            List<BookInfo> books = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getWriter().equals(this.getCredentials().getUsername())).collect(Collectors.toList());
            model.addAttribute("books", books);
            return "/writerPage";
        }
        return "/logorreg";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<BookInfo> books = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getWriter().equals(this.getCredentials().getUsername())).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "/writerPage";
    }
    @GetMapping("/authorFeedback")
    public String feedbcks(Model model){
        return "/authorFeedback";
    }

    @GetMapping("/feedback/{id}")
    private String feedback(@PathVariable("id")Integer id, Model model){
            List<Feedback> list=feedbackServiceR.getAllFeedbacks().stream().filter(feedback -> feedback.getIdBook().equals(id)).collect(Collectors.toList());
            model.addAttribute("feeds", list);
            return "/authorFeedback";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")Integer id, Model model){
       bookServiceW.deleteBook(bookServiceR.getBookById(id).get());
        List<BookInfo> books = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getWriter().equals(this.getCredentials().getUsername())).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "/writerPage";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("book", new BookDto());
        return "/addBook";
    }
    @PostMapping("/addBook")
    public String addBook(@Valid @ModelAttribute("book")BookDto bookDto, Model model, BindingResult result){
        if(result.hasErrors()){
            return "/hello";
        }else{
            Book book = new Book(bookDto.getName(), bookDto.getType(), bookDto.getDescriprion(), bookDto.getPrice(), this.getCredentials().getUsername());
            bookServiceW.addBook(book);

        }
        List<BookInfo> books = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getWriter().equals(this.getCredentials().getUsername())).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "/writerPage";
    }


}
