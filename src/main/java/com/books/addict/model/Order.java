package com.books.addict.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "deal")
public class Order implements Subject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idorder")
    private Integer idOrder;

    @NotNull
    @Column(name="idbook")
    private Integer idBook;

    @NotNull
    @Column(name="reader")
    private String reader;

    @NotNull
    @Column(name="state")
    private String state;

    @NotNull
    @Column(name="password")
    private String password;

    public Order(@NotNull Integer idBook, @NotNull String reader, @NotNull String password) {
        this.idBook = idBook;
        this.reader = reader;
        this.state="delivering";
        this.password = password;
    }

    public Order() {
        this.state="delivering";
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        this.notifyObserbers();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getIdOrder(), order.getIdOrder()) &&
                Objects.equals(getIdBook(), order.getIdBook()) &&
                Objects.equals(getReader(), order.getReader()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getPassword(), order.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdOrder(), getIdBook(), getReader(), getState(), getPassword());
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idBook=" + idBook +
                ", reader='" + reader + '\'' +
                ", state='" + state + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Transient
    private List<Observer> observers=new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserbers() {
        for(Observer observer:observers){
            System.out.println("obs");
            observer.update(this);
        }
    }

}
