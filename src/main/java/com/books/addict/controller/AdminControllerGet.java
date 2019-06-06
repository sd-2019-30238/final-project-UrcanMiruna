package com.books.addict.controller;


import com.books.addict.service.readService.OrderServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminPage")
public class AdminControllerGet {

    @Autowired
    private OrderServiceR orderServiceR;

    @GetMapping(path = "/all")
    public String showAll(Model model){
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/adminPage";
    }
    @GetMapping(path = "/validate")
    public String validate(Model model){
        return "/adminPage";
    }
}
