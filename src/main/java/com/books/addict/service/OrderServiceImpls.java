package com.books.addict.service;

import com.books.addict.model.Book;
import com.books.addict.model.Observer;
import com.books.addict.model.Order;
import com.books.addict.model.OrderRepository;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderServiceImpls extends Observer implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;


    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void addorder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }


    @Override
    public Float getTotalPrice(String username) {
        List<Order> orders=this.getOrderByReader(username);
        float price = 0.0f;
        for(Order order:orders){
            Optional<Book> book=bookService.getBookById(order.getIdBook());
            price+=book.get().getPrice();
        }
        return price;
    }

    @Override
    public void validateOrder(Order order) {
        if(order.getState().equalsIgnoreCase("delivering")){
            order.setState("paid");
            order.register(this);
            this.updateOrder(order);
        }
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getOrderByReader(String username) {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        orders = orders.stream().filter(order -> order.getReader().equalsIgnoreCase("username")).collect(Collectors.toList());
        return orders;
    }

    @Override
    public List<Book> getOrdersByAuthors(String username) {
        return bookService.getBooksByAuthor(username);
    }

    @Override
    public void update(Order order) {
        Optional<Book> book = bookService.getBookById(order.getIdBook());
        System.out.println("The order for the reader "+ order.getReader() +" for the book "+ bookService.getBookById(order.getIdBook()).get().getName() );
    }
}
