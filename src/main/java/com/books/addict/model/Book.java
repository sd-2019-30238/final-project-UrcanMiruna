package com.books.addict.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idbook")
    private Integer id;


    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name="type")
    private String type;

    @NotNull
    @Column(name = "decsription")
    private String descriprion;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "writer")
    private String writer;

    public Book(@NotNull String name, @NotNull String type, @NotNull String descriprion, @NotNull Double price, @NotNull String writer) {
        this.name = name;
        this.type = type;
        this.descriprion = descriprion;
        this.price = price;
        this.writer = writer;
    }

    public Book() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(getName(), book.getName()) &&
                Objects.equals(getType(), book.getType()) &&
                Objects.equals(getDescriprion(), book.getDescriprion()) &&
                Objects.equals(getPrice(), book.getPrice()) &&
                Objects.equals(getWriter(), book.getWriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), getDescriprion(), getPrice(), getWriter());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", descriprion='" + descriprion + '\'' +
                ", price=" + price +
                ", writer='" + writer + '\'' +
                '}';
    }
}
