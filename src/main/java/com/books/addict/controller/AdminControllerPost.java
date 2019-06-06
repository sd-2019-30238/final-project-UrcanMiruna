package com.books.addict.controller;


import com.books.addict.model.*;
import com.books.addict.service.readService.*;
import com.books.addict.service.writeService.*;
import net.bytebuddy.asm.Advice;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adminPage")
public class AdminControllerPost {

    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private BookServiceR bookServiceR;

    @Autowired
    private OrderServiceW orderServiceW;

    @Autowired
    private ReaderServiceW readerServiceW;

    @Autowired
    private ReaderServiceR readerServiceR;

    @Autowired
    private AdminServiceR adminServiceR;

    @Autowired
    private AdminServiceW adminServiceW;

    @Autowired
    private AuthorServiceW authorServiceW;

    @Autowired
    private AuthorServiceR authorServiceR;

    @Autowired
    private FeedbackServiceR feedbackServiceR;
    @Autowired
    private FeedbackServiceW feedbackServiceW;



    @PostMapping("/validate/{idOrder}")
    public String validate(@PathVariable("idOrder")Integer id, Model model){
        List<Order> orders=orderServiceR.getAllOrders();
        orderServiceW.validateOrder(orderServiceR.getOrderById(id).get());
        System.out.println("vc");
        model.addAttribute("orders", orders);
        return "/adminPage";

    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("admin", new AdminDto());
        return "/register";
    }

    @PostMapping("/register")
    public String register(ServletWebRequest r1, ServletWebRequest r2, ServletWebRequest r3, @Valid @ModelAttribute("admin")AdminDto adminDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/hello";
        }
        if (readerServiceR.findByUsername(adminDto.getUsername()) == null && authorServiceR.findByUsername(adminDto.getUsername()) == null && adminServiceR.findByUsername(adminDto.getUsername()) == null) {
            if (r1.getParameterValues("reader") != null && r2.getParameterValues("writer") == null && r3.getParameterValues("admin") == null) {
                Reader reader = new Reader(adminDto.getName(), adminDto.getAge(), adminDto.getUsername(), adminDto.getPassword());
                readerServiceW.addReader(reader);

                model.addAttribute("books", bookServiceR.getBooksInfos());
                List<Order> orders=orderServiceR.getAllOrders();
                List<Book> books=new ArrayList<>();
                for(Order order:orders){
                    if(order.getReader().equals(adminDto.getUsername())){
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
            } else {
                if (r1.getParameterValues("reader") == null && r2.getParameterValues("writer") != null && r3.getParameterValues("admin") == null) {
                    Author reader = new Author(adminDto.getName(), adminDto.getAge(), adminDto.getUsername(), adminDto.getPassword());
                    authorServiceW.addAuthor(reader);
                    List<BookInfo> books = bookServiceR.getBooksInfos().stream().filter(bookInfo -> bookInfo.getWriter().equals(adminDto.getUsername())).collect(Collectors.toList());
                    model.addAttribute("books", books);
                    return "/writerPage";
                } else {
                    if (r1.getParameterValues("reader") == null && r2.getParameterValues("writer") == null && r3.getParameterValues("admin") != null) {
                        Admin admin = new Admin(adminDto.getUsername(), adminDto.getPassword());
                        adminServiceW.addAdmin(admin);
                        model.addAttribute("orders", orderServiceR.getAllOrders());
                        return "/adminPage";
                    }
                }
            }

        }
        return "/logorreg";
    }

    @GetMapping("/feedback")
    public String getFeedback(Model model){
        model.addAttribute("feeds", feedbackServiceR.getAllFeedbacks());
        return "/adminFeedback";
    }

    @PostMapping("/feedback/{id}")
    public String getFeedback(@PathVariable("id")Integer id, Model model){
        Feedback feedback=feedbackServiceR.getFeedById(id).get();
        feedbackServiceW.deleteFeedback(feedback);
        model.addAttribute("feeds", feedbackServiceR.getAllFeedbacks());
        return "/adminFeedback";
    }
}
