package com.books.addict.controller;


import com.books.addict.service.readService.AdminServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AdminServiceR adminServiceR;
    @GetMapping("/")
    public String root(){
        return "/logorreg";
    }
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
    @GetMapping("/loginOthers")
    public String logg2(){
        return "/loginOthers";
    }
    @GetMapping("/logorreg")
    public String log(){
        return "/logorreg";
    }
}
