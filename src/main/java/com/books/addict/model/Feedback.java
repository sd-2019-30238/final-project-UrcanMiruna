package com.books.addict.model;


import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfeedback")
    private Integer id;

    @NotNull
    @Column(name = "idbook")
    private Integer idBook;

    @NotNull
    @Column(name="reader")
    private String reader;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "rate")
    private Integer rate;

    public Feedback(@NotNull Integer idBook, @NotNull String reader, @NotNull String description, @NotNull Integer rate) {
        this.idBook = idBook;
        this.reader = reader;
        this.description = description;
        this.rate = rate;
    }

    public Feedback() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(getId(), feedback.getId()) &&
                Objects.equals(getIdBook(), feedback.getIdBook()) &&
                Objects.equals(getReader(), feedback.getReader()) &&
                Objects.equals(getDescription(), feedback.getDescription()) &&
                Objects.equals(getRate(), feedback.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdBook(), getReader(), getDescription(), getRate());

    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", idBook=" + idBook +
                ", reader='" + reader + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
