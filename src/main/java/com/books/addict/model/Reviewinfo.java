package com.books.addict.model;


import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class Reviewinfo {

    private String description;
    private Integer rate;
    private Integer idBook;

    public Reviewinfo(String description, Integer rate) {
        this.description = description;
        this.rate = rate;
    }

    public Reviewinfo(String description, Integer rate, Integer idBook) {
        this.description = description;
        this.rate = rate;
        this.idBook = idBook;
    }

    public Reviewinfo() {
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }
}
