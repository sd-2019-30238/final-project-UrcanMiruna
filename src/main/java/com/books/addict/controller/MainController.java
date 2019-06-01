package com.books.addict.controller;


import com.books.addict.model.Admin;
import com.books.addict.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AdminService adminService;
    @GetMapping("/")
    public String root(){
        return "/logorreg";
    }
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

}
