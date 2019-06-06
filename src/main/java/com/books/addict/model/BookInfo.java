package com.books.addict.model;

public class BookInfo {

    private Integer id;

    private String name;

    private String type;

    private String descriprion;

    private Double price;

    private String writer;
    private double rate;

    public BookInfo(Integer id, String name, String type, String descriprion, Double price, String writer, double rate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.descriprion = descriprion;
        this.price = price;
        this.writer = writer;
        this.rate=rate;
    }

    public BookInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
       this.rate=rate;
    }


}
