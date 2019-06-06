package com.books.addict.model;

public class BookDto {
    private String name;
    private String type;
    private String descriprion;
    private Double price;


    public BookDto(String name, String type, String descriprion, Double price) {
        this.name = name;
        this.type = type;
        this.descriprion = descriprion;
        this.price = price;
    }

    public BookDto() {
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

    public void setDescriprion(String descriptrion) {
        this.descriprion = descriptrion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
