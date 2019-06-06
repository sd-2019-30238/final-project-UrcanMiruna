package com.books.addict.controller;


import com.books.addict.model.*;
import com.books.addict.service.readService.BookServiceR;
import com.books.addict.service.readService.FeedbackServiceR;
import com.books.addict.service.readService.OrderServiceR;
import com.books.addict.service.readService.ReaderServiceR;
import com.books.addict.service.writeService.BookServiceW;
import com.books.addict.service.writeService.FeedbackServiceW;
import com.books.addict.service.writeService.OrderServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/readerPage")
public class ReaderControllerGet {

    @Autowired
    private BookServiceR bookServiceR;

    @Autowired
    private FeedbackServiceR feedbackServiceR;
    @Autowired
    private ReaderServiceR readerServiceR;
    private Credentials credentials;
    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private BookServiceW bookServiceW;
    @Autowired
    private OrderServiceW orderServiceW;
    @Autowired
    private FeedbackServiceW feedbackServiceW;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("books", bookServiceR.getBooksInfos());
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order:orders){
            if(order.getReader().equals(credentials.getUsername())){
                Optional<Book> book=bookServiceR.getBookById(order.getIdBook());
                books.add(book.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/feedbackPage")
    public String feedbcks(Model model){
        return "/feedbackPage";
    }
    @GetMapping("/loginOthers")
    public String login(){
        return "/loginOthers";
    }
    @PostMapping("/getFeedback/{id}")
    public String getFeedback(@PathVariable("id")Integer id, Model model){
        List<Feedback> list=feedbackServiceR.getAllFeedbacks().stream().filter(feedback -> feedback.getIdBook().equals(id)).collect(Collectors.toList());
       /* List<BookInfo> infos=new ArrayList<>();
        Optional<Book> book = bookServiceR.getBookById(id);
        double rates=feedbackServiceR.rates(book.get());
        BookInfo bookInfo=new BookInfo(book.get().getId(), book.get().getName(), book.get().getType(), book.get().getDescriprion(),book.get().getPrice(), book.get().getWriter(),rates);*/
        model.addAttribute("feeds", list);
        return "/feedbackPage";
    }

    @PostMapping("/loginOthers")
    public String login(@Valid @ModelAttribute("logg")Credentials credentials, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("eroare");
            return "/logorreg";
        }
        if(readerServiceR.findByUsername(credentials.getUsername())!=null && readerServiceR.findByUsername(credentials.getUsername()).getPassword().equals(credentials.getPassword())){
            this.credentials=credentials;
            model.addAttribute("books", bookServiceR.getBooksInfos());
            List<Order> orders=orderServiceR.getAllOrders();
            List<Book> books=new ArrayList<>();
            for(Order order:orders){
                if(order.getReader().equals(credentials.getUsername())){
                    Optional<Book> book=bookServiceR.getBookById(order.getIdBook());
                   try{
                       books.add(book.get());
                   }catch (Exception d){
                       d.getMessage();
                   }
                }
            }
            model.addAttribute("bookR", books);
            return "/readerPage";
        }
        return "/logorreg";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")Integer id, Model model){
        Optional<Book> book=bookServiceR.getBookById(id);
        List<Order> orders1= orderServiceR.getAllOrders();
        List<Order> orderss=  orders1.stream().filter(order1 -> order1.getIdBook().equals(id)).filter(order1 -> order1.getReader().equals(this.getCredentials().getUsername())).collect(Collectors.toList());
        orderServiceW.deleteOrder(orderss.get(0));
        model.addAttribute("books", bookServiceR.getBooksInfos());
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order:orders){
            if(order.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }

    @PostMapping("/addBook/{id}")
    public String addBook(@PathVariable("id")Integer id, Model model){
        Reader reader=readerServiceR.findByUsername(this.getCredentials().getUsername());
        Order order=new Order(id, reader.getUsername(), reader.getPassword());
        orderServiceW.addorder(order);

        model.addAttribute("books", bookServiceR.getBooksInfos());
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }

    @GetMapping("/review")
    public String review(Model model){
        model.addAttribute("reviewInfo", new Reviewinfo());

        return "/reviewPage";
    }

    @PostMapping("/review")
    public String review(@Valid @ModelAttribute("reviewInfo")Reviewinfo reviewinfo, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/logorreg";
        }
        Feedback feedback=new Feedback(reviewinfo.getIdBook(),this.getCredentials().getUsername(),reviewinfo.getDescription(), reviewinfo.getRate());
        feedbackServiceW.addFeedback(feedback);

        model.addAttribute("books", bookServiceR.getBooksInfos());
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }

    @GetMapping("/mystery")
    public String mystery(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("mystery")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/adventure")
    public String adventure(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("adventure")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/fiction")
    public String fiction(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("sf")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/history")
    public String history(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("history")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/comic")
    public String comic(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("comic")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/poetry")
    public String poetry(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getType().equalsIgnoreCase("poetry")).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/ascending")
    public String ascending(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().sorted(Comparator.comparing(BookInfo::getPrice)).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/rate")
    public String descending(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().sorted(Comparator.comparing(BookInfo::getRate)).collect(Collectors.toList());
        Collections.reverse(ms);
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }
    @GetMapping("/author")
    public String author(Model model){
        List<BookInfo> ms = bookServiceR.getBooksInfos().stream().sorted(Comparator.comparing(BookInfo::getWriter)).collect(Collectors.toList());
        model.addAttribute("books", ms);
        List<Order> orders=orderServiceR.getAllOrders();
        List<Book> books=new ArrayList<>();
        for(Order order1:orders){
            if(order1.getReader().equals(credentials.getUsername())){
                Optional<Book> book1=bookServiceR.getBookById(order1.getIdBook());
                books.add(book1.get());
            }
        }
        model.addAttribute("bookR", books);
        return "/readerPage";
    }

}
